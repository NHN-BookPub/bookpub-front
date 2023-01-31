function findAddress() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.getElementById("roadAddress").value = data.roadAddress;
        }
    }).open();
}

function saveAddress(id) {
    let num = id.substring(7);
    let roadAddr = 'roadAddr' + num;
    let detailAddr = 'detailAddr' + num;

    let roadText = document.getElementById(roadAddr).innerText;
    let detailText = document.getElementById(detailAddr).innerText;

    document.getElementById("roadAddress").value = roadText
    document.getElementById("detailAddress").value = detailText

    var modal = document.getElementById("member_addresses");
    modal.style.display = 'none';
}

function usePoint() {
    let relievedPoint = document.getElementById("usePoint")
    let useUserPoint = document.getElementById("tbl-point")
    let totalAmount = document.getElementById("totalAmount")

    let userPoint = document.getElementById("userPoint").innerText;
    if (parseInt(userPoint) < useUserPoint.value) {
        alert("포인트가 부족합니다.")
        relievedPoint.innerText = "0";
        useUserPoint.value = "0";
        calTotalAmount()
    } else {
        alert("포인트를 사용합니다.")
        if (parseInt(totalAmount.innerText) < useUserPoint.value) {
            relievedPoint.innerText = totalAmount.innerText
        } else {
            relievedPoint.innerText = useUserPoint.value
        }
        calTotalAmount();
    }
}

function gift() {
    let giftPrice = document.getElementById("gift-price");

    let giftOption = document.getElementById('gift');
    if (giftOption.checked === true) {
        giftPrice.innerText = "2000";
        calTotalAmount();
    } else {
        giftPrice.innerText = "0";
        calTotalAmount();
    }
}

function calTotalAmount() {
    let commodity = document.getElementById("commodity")
    let totalAmount = document.getElementById("totalAmount")
    let relievedPoint = document.getElementById("usePoint")
    let couponDiscount = document.getElementById("couponDiscount")
    let giftPrice = document.getElementById("gift-price");
    let savingPoint = document.getElementById("savingPoint");

    let result = parseInt(giftPrice.innerText) + parseInt(commodity.innerText) -
        (parseInt(relievedPoint.innerText) +
            parseInt(couponDiscount.innerText));

    totalAmount.innerText = result.toString();
    savingPoint.innerText = ((result / 7).toFixed()).toString();
}

document.addEventListener("DOMContentLoaded", function () {
    let totalCnt = document.getElementById("totalCnt");
    let commodity = document.getElementById("commodity");
    let totalAmount = document.getElementById("totalAmount");

    let cntList = document.querySelectorAll('.prd_num');
    let priceList = document.querySelectorAll('.product-price');

    let count = 0;
    let totalPrice = 0;

    for (var i=0; i<cntList.length; i++) {
        count += parseInt(cntList[i].innerText)
    }

    for(var i=0; i<priceList.length; i++){
        totalPrice += parseInt(priceList[i].innerText)
    }

    totalCnt.innerText = count.toString() + "개";
    commodity.innerText = totalPrice.toString();
    totalAmount.innerText = totalPrice.toString();
})
