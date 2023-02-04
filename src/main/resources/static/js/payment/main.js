function PaymentBtn(method, toss, order) {
    var tossPayments = TossPayments("test_ck_YoEjb0gm23PQOaKMp0jVpGwBJn5e")

    console.log("실행")
    tossPayments.requestPayment('카드', {
        amount: 3500,
        orderId: "testetstset",
        orderName: "teststsetsf",
        successUrl: "http://localhost:8080/payment/success",
        failUrl: "http://localhost:8080/payment/fail"
    })
}