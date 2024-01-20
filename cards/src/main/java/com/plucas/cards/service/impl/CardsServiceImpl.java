package com.plucas.cards.service.impl;

import com.plucas.cards.constants.CardsConstants;
import com.plucas.cards.dto.CardsDTO;
import com.plucas.cards.entity.Cards;
import com.plucas.cards.exception.CardAlreadyExistsException;
import com.plucas.cards.exception.ResourceNotFoundException;
import com.plucas.cards.mapper.CardsMapper;
import com.plucas.cards.repository.CardsRepository;
import com.plucas.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
        if(cards.isPresent()) {
            throw new CardAlreadyExistsException("Card already exists for the given mobile number" + mobileNumber);
        }

        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    @Override
    public CardsDTO fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards, new CardsDTO());
    }

    /**
     *
     * @param cardsDTO - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardsDTO cardsDTO) {
        Cards cards = cardsRepository.findByCardNumber(cardsDTO.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDTO.getCardNumber()));
        CardsMapper.mapToCards(cardsDTO, cards);
        cardsRepository.save(cards);
        return  true;
    }

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of card details is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }

}
