package com.hyewon.springplayground2.domain.stock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createStock(Long id, Long quantity) {
        Stock stock = new Stock(id, quantity);
        stock.increase(quantity);
        stockRepository.saveAndFlush(stock);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decrease(Long id, Long quantity) {
         Stock stock = stockRepository.findByProductId(id);
         stock.decrease(quantity);
         stockRepository.saveAndFlush(stock);
    }
}
