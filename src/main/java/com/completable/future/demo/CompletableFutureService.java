package com.completable.future.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class CompletableFutureService {

    public Future<String> calculateAsync() {
        System.out.println("calculateAsync function thread id: " + Thread.currentThread().getId());
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Inside thread, id: " + Thread.currentThread().getId());
                System.out.println("Before sleep");
                Thread.sleep(5000);
                System.out.println("After sleep");
                return "Done";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Future<String> processResultOfAsync() {
        return CompletableFuture
                .completedFuture("Hello")
                .thenApply(result -> result + " World");
    }

    public Future<String> processResultOfAsyncUsingCompose() {
        return CompletableFuture
                .completedFuture("Hello")
                .thenCompose(result -> CompletableFuture.completedFuture(result + " World"));
    }

    public CompletableFuture[] runFuturesInParallel() throws Exception {
        final var futures = new CompletableFuture[] { futureString(), futureInt(), futureDouble() };
        final var combinedFuture = CompletableFuture.allOf(futures);
        System.out.println("Before combinedFuture get");
        combinedFuture.get();
        System.out.println("After combinedFuture get");
        return futures;
    }

    public Future<String> handleFutureError() {
        final var future = new CompletableFuture<String>();
        future.completeExceptionally(new RuntimeException("Failed"));
        return future.handle((result, error) -> error != null? error.getMessage() : result);
    }

    private CompletableFuture<String> futureString() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("futureString before sleep");
                Thread.sleep(5000);
                System.out.println("futureString after sleep");
                return "Hello";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private CompletableFuture<Integer> futureInt() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("futureInt before sleep");
                Thread.sleep(5000);
                System.out.println("futureInt after sleep");
                return 1;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private CompletableFuture<Double> futureDouble() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("futureDouble before sleep");
                Thread.sleep(5000);
                System.out.println("futureDouble after sleep");
                return 1.1;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
