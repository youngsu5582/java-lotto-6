package lotto.controller;

import lotto.domain.lotto.LottoDraw;
import lotto.domain.lotto.LottoPaper;

import lotto.domain.payment.Payment;

import lotto.domain.prize.Prize;
import lotto.domain.prize.Statistic;

import lotto.domain.result.LottoResult;

public class GameController {
    PaymentController paymentController = new PaymentController();
    LottoController lottoController = new LottoController();
    LottoPaperController lottoPaperController = new LottoPaperController();
    LottoResultController lottoResultController = new LottoResultController();
    PrizeController prizeController = new PrizeController();
    StatisticController statisticController = new StatisticController();

    public LottoPaper purchaseLotto() {
        Payment payment = paymentController.processPayment();
        LottoPaper lottoPaper = lottoPaperController.purchaseLottoPaper(payment);
        return lottoPaper;
    }

    public LottoResult confirmResult(LottoPaper lottoPaper) {
        LottoDraw lottoDraw = lottoController.createLottoDraw();
        LottoResult lottoResult = lottoResultController.evaluateLottoResult(lottoPaper, lottoDraw);
        return lottoResult;
    }

    public Prize receivePrize(LottoResult lottoResult) {
        Prize prize = prizeController.calculatePrize(lottoResult);
        return prize;
    }

    public void checkYield(LottoPaper lottoPaper, Prize prize) {
        Statistic statistic = statisticController.calculateStatistic(lottoPaper, prize);
        statisticController.printStatistic(statistic);
    }

}
