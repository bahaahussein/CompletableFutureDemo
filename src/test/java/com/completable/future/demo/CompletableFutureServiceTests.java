package com.completable.future.demo;

import org.junit.Assert;
import org.junit.Test;

public class CompletableFutureServiceTests {

    @Test
    public void calculateAsync() throws Exception {
        final var completableFutureService = new CompletableFutureService();
        final var future = completableFutureService.calculateAsync();
        System.out.println("Before Future get");
        final var resultString = future.get();
        System.out.println("After Future get");
        Assert.assertEquals("Done", resultString);
    }

    @Test
    public void processResultOfAsync() throws Exception {
        final var completableFutureService = new CompletableFutureService();
        final var future = completableFutureService.processResultOfAsync();
        Assert.assertEquals("Hello World", future.get());
    }

    @Test
    public void processResultOfAsyncUsingCompose() throws Exception {
        final var completableFutureService = new CompletableFutureService();
        final var future = completableFutureService.processResultOfAsyncUsingCompose();
        Assert.assertEquals("Hello World", future.get());
    }

    @Test
    public void runFuturesInParallel() throws Exception {
        final var completableFutureService = new CompletableFutureService();
        final var futures = completableFutureService.runFuturesInParallel();
        Assert.assertTrue(futures[0].isDone());
        Assert.assertTrue(futures[1].isDone());
        Assert.assertTrue(futures[2].isDone());
        Assert.assertEquals("Hello", futures[0].get());
        Assert.assertEquals(1, futures[1].get());
        Assert.assertEquals(1.1, futures[2].get());
    }

    @Test
    public void handleFutureError() throws Exception {
        final var completableFutureService = new CompletableFutureService();
        final var future = completableFutureService.handleFutureError();
        Assert.assertEquals("Failed", future.get());
    }
}
