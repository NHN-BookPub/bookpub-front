function paymentBtn(method, toss, order) {
    var tossPayments = TossPayments(toss.clientId)
    tossPayments.requestPayment(method, {
        amount: order.totalAmount,
        orderId: order.orderId,
        orderName: order.orderName,
        successUrl: toss.successUrl,
        failUrl: toss.failUrl
    })
}