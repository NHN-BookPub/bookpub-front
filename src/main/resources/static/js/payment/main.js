function paymentBtn(method, toss, order) {
    var tossPayments = TossPayments(toss.clientId)

    console.log(method)

    tossPayments.requestPayment(method, {
        amount: order.totalAmount,
        orderId: "orderIdOrderId",
        orderName: "orderName",
        successUrl: toss.successUrl,
        failUrl: toss.failUrl
    })
}

function paySuccess() {
    window.location.href = "#";
}