package backEnd.general.dealers;

import backEnd.general.KeySequenceService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service for the dealer.
 *
 * @author jonathan
 */
@Service
public class DealerService {

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private KeySequenceService keySequenceService;

    @Autowired
    private DealerValidate dealerValidate;

    /**
     * Get a dealer Json using the primary key.
     *
     * @param primaryKey - the primary key to look for
     * @return - the dealer found
     * @throws DealerException - an error if the dealer is not found
     */
    @Transactional
    public DealerJson getDealerJsonByKey(String primaryKey) throws DealerException {
        Dealer dealer = null;

        try {
            dealer = dealerRepository.findOne(primaryKey);

            if (dealer == null) {
                throw new DealerException(DealerException.DEALER_KEY_NOT_FOUND + primaryKey);
            }
        } catch (NoResultException nre) {
            throw new DealerException(DealerException.DEALER_KEY_NOT_FOUND + primaryKey);
        }

        DealerJson dealerJson = toJson(dealer);

        return dealerJson;
    }

    /**
     * Get a dealer Json by the dealer name.
     *
     * @param name - the name to look for
     * @return - the dealer found
     * @throws DealerException - an error if the dealer is not found
     */
    @Transactional
    public DealerJson getDealerJsonByName(String name) throws DealerException {
        Dealer dealer = null;

        try {
            dealer = dealerRepository.findByName(name);

            if (dealer == null) {
                throw new DealerException(DealerException.DEALER_NAME_NOT_FOUND + name);
            }
        } catch (NoResultException nre) {
            throw new DealerException(DealerException.DEALER_NAME_NOT_FOUND + name);
        }

        DealerJson dealerJson = toJson(dealer);

        return dealerJson;
    }

    /**
     * Save the passed dealer.
     *
     * @param dealerJson - the dealer info to save
     * @throws DealerException - errors found with the dealer info to save
     */
    @Transactional
    public void saveDealer(DealerJson dealerJson) throws DealerException {
        dealerValidate.validateDealerSave(dealerJson);

        if (dealerJson.getPrimaryKey().trim().isEmpty()) {
            String primaryKey = keySequenceService.getNextKey("DEALERS", "DEA");
            dealerJson.setPrimaryKey(primaryKey);
        }

        Dealer dealer = toDealer(dealerJson);

        dealerRepository.saveAndFlush(dealer);
    }

    /**
     * Delete the dealer.
     *
     * @param primaryKey - the primary key of the dealer to delete
     * @throws DealerException - errors found when trying to delete the dealer
     */
    @Transactional
    public void deleteDealer(String primaryKey) throws DealerException {
        dealerValidate.validateDealerDelete(primaryKey);

        dealerRepository.delete(primaryKey);
    }

    /**
     * Get a list of dealers.
     *
     * @return - the list of dealers
     */
    @Transactional
    public List<DealerJson> getDealerList() {
        List<DealerJson> dealerJsons = new ArrayList<DealerJson>();

        List<Dealer> dealers = dealerRepository.findAll();

        for (Dealer dealer : dealers) {
            DealerJson dealerJson = toJson(dealer);

            dealerJsons.add(dealerJson);

        }

        return dealerJsons;
    }

    /**
     * Get a list of dealers for the passed country key.
     *
     * @param countryKey - the country key to filter by
     * @return - the list of dealers found
     */
    @Transactional
    public List<DealerJson> getDealerListByCountryKey(String countryKey) {
        List<DealerJson> dealerJsons = new ArrayList<DealerJson>();

        List<Dealer> dealers = dealerRepository.findAllByCountryKey(countryKey);

        for (Dealer dealer : dealers) {
            DealerJson dealerJson = toJson(dealer);

            dealerJsons.add(dealerJson);

        }

        return dealerJsons;
    }

    /**
     * Reset the last key in the key sequence table for the dealers table to the
     * highest key.
     */
    @Transactional
    public void resetKeys() {
        String maxKey = dealerRepository.getMaxKey();
        int maxKeyValue = 0;

        if (!(maxKey == null) && !(maxKey.trim().isEmpty())) {
            maxKeyValue = Integer.parseInt(maxKey.substring(3));
        }

        keySequenceService.resetKeyValue("DEALERS", maxKeyValue);
    }

    private DealerJson toJson(Dealer dealer) {
        DealerJson dealerJson = new DealerJson();
        dealerJson.setPrimaryKey(dealer.getPrimaryKey());
        dealerJson.setName(dealer.getName());
        dealerJson.setCountryKey(dealer.getCountryKey());

        return dealerJson;
    }

    private Dealer toDealer(DealerJson dealerJson) {
        Dealer dealer = new Dealer();
        dealer.setPrimaryKey(dealerJson.getPrimaryKey());
        dealer.setName(dealerJson.getName());
        dealer.setCountryKey(dealerJson.getCountryKey());

        return dealer;
    }
}
