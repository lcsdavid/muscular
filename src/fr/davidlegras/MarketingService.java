package fr.davidlegras;

import java.util.concurrent.Flow;

public class MarketingService implements Flow.Subscriber<Integer> {



    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
