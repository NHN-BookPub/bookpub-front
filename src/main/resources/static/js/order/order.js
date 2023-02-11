let couponConfirmList = new Set();
const policy_actualPrice = '실구매가';
const policy_salePrice = '판매가';

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
    let userPoint = parsingNumber(document.getElementById("userPoint").innerText);

    let usePoint = 0;
    if (useUserPoint.value !== "") {
        usePoint = useUserPoint.value;
    }

    let couponDiscount = document.getElementById("couponDiscount");
    let value = parsingNumber(couponDiscount.innerText);

    // if (value === '0') {
    //     alert("쿠폰을 먼저 적용해주세요!")
    //     relievedPoint.innerText = "0";
    //     useUserPoint.value = 0;
    // } else

    if (parseInt(userPoint) < usePoint) {
        alert("포인트가 부족합니다.")
        relievedPoint.innerText = "0";
        useUserPoint.value = "";
        calTotalAmount()
    } else {
        alert("포인트를 사용합니다.")
        let originPrice = parseInt(parsingNumber(totalAmount.innerText)) +
            parseInt(parsingNumber(relievedPoint.innerText))

        if (originPrice < usePoint) {
            relievedPoint.innerText = parseKRW(originPrice.toString());
            useUserPoint.value = originPrice;
        } else {
            relievedPoint.innerText = parseKRW(usePoint.toString());
        }
        calTotalAmount();
    }

}

function amountLogicCheck_gift(packPolicy) {
    let totalAmount = document.getElementById("totalAmount");
    if (parseInt(totalAmount.innerText) === 100) {
        let relievedPoint = document.getElementById("usePoint")
        let useUserPoint = document.getElementById("tbl-point")

        let result = relievedPoint.innerText;
        result = (parseInt(parsingNumber(result)) - packPolicy.policyFee)

        if (result < 0) {
            result = 0;
        }

        relievedPoint.innerText = parseKRW(result.toString());
        useUserPoint.value = result
    }
}

function giftOp(packPolicy) {
    let giftPrice = document.getElementById("gift-price");
    let policyFee = packPolicy.policyFee

    let giftOption = document.getElementById('gift');
    if (giftOption.checked === true) {
        giftPrice.innerText = parseKRW(policyFee.toString());
        calTotalAmount();
    } else {
        giftPrice.innerText = "0";
        amountLogicCheck_gift(packPolicy);
        calTotalAmount();
    }
}

function calTotalAmount() {
    let commodity = document.getElementById("commodity")
    let totalAmount = document.getElementById("totalAmount")
    let relievedPoint = document.getElementById("usePoint")
    let couponDiscount = document.getElementById("couponDiscount")
    let giftPrice = document.getElementById("gift-price");
    let deliveryAmount = document.getElementById("shipAmount");

    let result = parseInt(parsingNumber(commodity.innerText)) +
        parseInt(parsingNumber(giftPrice.innerText)) +
        parseInt(parsingNumber(deliveryAmount.innerText)) -
        (parseInt(parsingNumber(relievedPoint.innerText)) +
            parseInt(parsingNumber(couponDiscount.innerText)));


    if (result === 0) {
        if (parseInt(parsingNumber(relievedPoint.innerText)) >= 100) {
            let point = parseInt(parsingNumber(relievedPoint.innerText)) - 100;
            relievedPoint.innerText = parseKRW(point.toString());
            let input = document.getElementById("tbl-point")
            input.value = point;
        } else {
            let coupon = parseInt(parsingNumber(couponDiscount.innerText)) - 100;
            couponDiscount.innerText = parseKRW(coupon.toString());
        }
        result = result + 100;
    }

    totalAmount.innerText = parseKRW(result.toString());
}

function selectCoupon(product) {
    paintCouponModalPage(product)
    document.getElementById("coupon-select").style.display = "flex"
}

function paintCouponModalPage(product) {
    let container = document.getElementById("coupon-list")
    container.innerText = null;

    let couponList = product.couponList


    for (let i = 0; i < couponList.length; i++) {
        if (couponConfirmList.has(couponList[i].couponNo.toString())) {
            continue;
        }
        let price = product.salesPrice;

        if (price < couponList[i].policyMinimum) {
            continue;
        }
        let salePrice =
            couponList[i].policyFixed ?
                couponList[i].policyPrice :
                (product.salesPrice * (1 - (couponList[i].policyPrice / 100))).toFixed()

        let tr = document.createElement("tr")

        let discount =
            couponList[i].policyFixed ? couponList[i].policyPrice + "원" : couponList[i].policyPrice + "%"

        let sale = couponList[i].policyFixed ? salePrice : product.salesPrice - salePrice;

        if (sale > couponList[i].maxDiscount) {
            sale = couponList[i].maxDiscount
        }

        if (sale > price) {
            sale = price;
        }

        let inputValue = product.productNo + "|" +
            "-" +
            couponList[i].couponNo + "|" +
            couponList[i].templateName + "|" +
            sale;

        let inputId = product.productNo + "|" + couponList[i].couponNo;

        let td1 = document.createElement("td")
        td1.className = "left"
        let radioInput = document.createElement("input")
        radioInput.className = "n-radio"
        radioInput.value = inputValue
        radioInput.name = "coupon-info"
        radioInput.type = "radio"
        radioInput.id = inputId
        let label = document.createElement("label")
        label.htmlFor = inputId
        label.innerText = couponList[i].templateName
        let td2 = document.createElement("td")
        td2.className = "coupon-td"
        let span = document.createElement("span")
        span.className = "coupon-span"
        span.innerText = "1개"
        let td3 = document.createElement("td")
        td3.className = "coupon-td"
        let span2 = document.createElement("span")
        span2.className = "coupon-span"
        span2.innerText = discount;
        let td4 = document.createElement("td")
        td4.className = "coupon-td"
        let span3 = document.createElement("span")
        span3.className = "coupon-span"
        span3.innerText = parseKRW(sale.toString());

        td1.appendChild(radioInput)
        td1.appendChild(label)
        td2.appendChild(span)
        td3.appendChild(span2)
        td4.appendChild(span3)

        tr.appendChild(td1)
        tr.appendChild(td2)
        tr.appendChild(td3)
        tr.appendChild(td4)

        container.appendChild(tr)
    }
}

function applyCoupon() {
    let id = $('input[name="coupon-info"]:checked').get(0).value
    let split = id.split("-");
    let couponProductSplit = split[0];
    let productNo = couponProductSplit.split("|")[0]

    let hiddenInputId = productNo.toString() + "coupon"
    let hiddenInput = document.getElementById(hiddenInputId)

    let couponPart = split[1].split("|");
    let couponNumber = couponPart[0];
    let couponName = couponPart[1];
    let salesPrice = couponPart[2];
    let inputValue = couponNumber + ":" + couponName;
    hiddenInput.value = id

    couponConfirmList.add(couponNumber);

    let liName = "apply_coupon" + productNo;
    let couponInfo = document.getElementById(liName)
    couponInfo.innerText = inputValue;

    let saleId = "price" + productNo;
    let saleInfo = document.getElementById(saleId)
    saleInfo.innerText = parseKRW(salesPrice.toString()) + "원";

    let availBtnId = "coupon_avail_button" + productNo
    let cancelBtnId = "dc_cancel_button" + productNo

    let couponAddBtn = document.getElementById(availBtnId)
    couponAddBtn.style.display = "none"

    let cancelBtn = document.getElementById(cancelBtnId)
    cancelBtn.style.display = "block"

    let modal = document.getElementById("coupon-select")
    modal.style.display = "none"
}

function cancelCoupon(product) {
    let availBtnId = "coupon_avail_button" + product.productNo
    let cancelBtnId = "dc_cancel_button" + product.productNo

    let couponAddBtn = document.getElementById(availBtnId)
    couponAddBtn.style.display = "block"

    let cancelBtn = document.getElementById(cancelBtnId)
    cancelBtn.style.display = "none"

    let inputHiddenId = product.productNo + "coupon"

    let hiddenInput = document.getElementById(inputHiddenId)
    hiddenInput.value = ""

    let liName = "apply_coupon" + product.productNo;
    let couponInfo = document.getElementById(liName)
    let couponText = couponInfo.innerText
    let couponNumber = couponText.split(":")[0]

    couponConfirmList.delete(couponNumber);
    couponInfo.innerText = "";

    let saleId = "price" + product.productNo;
    let saleInfo = document.getElementById(saleId)
    saleInfo.innerText = "";
}

function couponCalculateAndParsingLogic() {
    var productList = []
    var couponList = []
    var resultList = []
    var productSalePriceList = []
    let totalSalePrice = 0;

    $('input[name="order-product"]').each(function () {
        productList.push($(this).val());
    });
    $('input[name="coupon-apply"]').each(function () {
        couponList.push($(this).val());
    });

    let changePriceList = document.querySelectorAll(".changePrice");
    let productPriceList = document.querySelectorAll('.product-total-price');
    let productDiscountList = document.querySelectorAll('.product-per-discount');

    for (var i = 0; i < productList.length; i++) {
        resultList.push(productList[i] + "- ");
        for (var j = 0; j < couponList.length; j++) {
            let productInfo = productList[i].split("|");
            let productNo = productInfo[0]
            let productCount = productInfo[1]
            let productPrice = productInfo[2]

            productSalePriceList.push(productPrice);
            if (couponList[j] === "") {
                continue;
            }

            let couponParsingProductNo = couponList[i].split("|")[0]

            if (productNo === couponParsingProductNo) {
                let couponInfo = couponList[i].split("-")[1]
                let discountAmount = couponInfo.split("|")[2]
                resultList[i] = (resultList[i] + couponInfo);
                productSalePriceList[i] -= parseInt(discountAmount)
                totalSalePrice += parseInt(discountAmount)
                productPriceList[i].innerText = parseKRW(productSalePriceList[i].toString()) + '원';
                productDiscountList[i].innerText = '-' + parseKRW(discountAmount.toString()) + '원'
                break;
            }
        }
    }

    for (var i = 0; i < changePriceList.length; i++) {
        changePriceList[i].value = productSalePriceList[i];
    }

    calculateSavePoint()
    return {resultList, totalSalePrice};
}

function applyCouponOrder() {
    const __ret = couponCalculateAndParsingLogic();
    var resultList = __ret.resultList;
    let totalSalePrice = __ret.totalSalePrice;

    let result = document.getElementById("product-coupon-result")
    let couponDiscount = document.getElementById("couponDiscount")

    result.value = resultList;
    couponDiscount.innerText = parseKRW(totalSalePrice.toString())

    amountLogicCheck_coupon(totalSalePrice);
    calTotalAmount()

    var modal = document.getElementById("coupon")
    modal.style.display = "none"
}

function amountLogicCheck_coupon(totalSalePrice) {
    let totalAmount = document.getElementById("totalAmount");
    let commodity = document.getElementById('commodity');
    let relievedPoint = document.getElementById("usePoint")
    let useUserPoint = document.getElementById("tbl-point")
    let result = relievedPoint.innerText;

    if (parseInt(parsingNumber(totalAmount.innerText)) === 100) {
        result = (parseInt(parsingNumber(result)) - totalSalePrice)

        if (result < 0) {
            result = 0;
        }
        relievedPoint.innerText = parseKRW(result.toString());
        useUserPoint.value = result
    }

    if (parseInt(parsingNumber(totalAmount.innerText)) - totalSalePrice < 100) {
        alert("최대 할인 금액을 넘어 포인트 사용을 취소합니다")
        relievedPoint.innerText = '0';
        useUserPoint.value = 0;
    }


}

function finalLogic() {
    couponCalculateAndParsingLogic()

    let pointResult = document.getElementById("pointAmount")
    let couponResult = document.getElementById("couponAmount")
    let totalResult = document.getElementById("totalPrice")
    let savePoint = document.getElementById("savePoint")

    let point = document.getElementById("usePoint")
    let coupon = document.getElementById("couponDiscount")
    let totalPrice = document.getElementById("totalAmount")
    let save = document.getElementById("savingPoint")

    pointResult.value = parsingNumber(point.innerText);
    couponResult.value = parsingNumber(coupon.innerText);
    totalResult.value = parsingNumber(totalPrice.innerText);
    savePoint.value = parsingNumber(save.innerText);
}

function parseKRW(data) {
    return data.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
}

function parsingNumber(data) {
    return data.replaceAll(",", "")
}

function calculateSavePoint() {
    let save = document.getElementById("savingPoint")

    let saveList = document.querySelectorAll(".prd_savePoint")
    let policyList = document.querySelectorAll(".point_policy")
    let priceList = document.querySelectorAll(".changePrice");
    let methodList = document.querySelectorAll(".policy-method")

    let totalSave = 0;

    for (var i = 0; i < policyList.length; i++) {
        let policy = policyList[i].value.split('|');
        let rate;
        let method = policy[0];
        let isSaved = policy[1];

        if (method === policy_salePrice && saveList[i].innerText !== '') {
            totalSave += parseInt(parsingNumber(saveList[i].innerText));
            continue;
        }
        if (isSaved === true) {
            if (methodList[i].innerText === '') {
                methodList[i].innerText = '적립방식 : ' + method;
            }

            rate = policy[2];
            let savePoint = parseInt(parsingNumber(priceList[i].value)) * (rate / 100);
            saveList[i].innerText = parseKRW((savePoint.toFixed()).toString())
            totalSave += savePoint;
        } else {
            if (methodList[i].innerText === '') {
                methodList[i].innerText = '포인트 적립이 안되는 상품입니다';
            }
        }
    }

    save.innerText = parseKRW((totalSave.toFixed()).toString());
}

document.addEventListener("DOMContentLoaded", function () {
    let totalCnt = document.getElementById("totalCnt");
    let commodity = document.getElementById("commodity");
    let totalAmount = document.getElementById("totalAmount");

    let cntList = document.querySelectorAll('.prd_num');
    let priceList = document.querySelectorAll('.product-price');
    let titleList = document.querySelectorAll('.prod_name');

    let count = 0;
    let totalPrice = 0;

    for (var i = 0; i < cntList.length; i++) {
        count += parseInt(parsingNumber(cntList[i].innerText))
    }

    for (var i = 0; i < priceList.length; i++) {
        totalPrice += parseInt(parsingNumber(priceList[i].innerText))
    }

    commodity.innerText = parseKRW(totalPrice.toString());
    totalCnt.innerText = count.toString() + "개";

    let shipAmount = document.getElementById("shipAmount")

    totalPrice += parseInt(parsingNumber(shipAmount.innerText))
    totalAmount.innerText = parseKRW(totalPrice.toString());

    let mainTitle = titleList[0].innerText;
    let orderName = count !== 1 ? mainTitle + ' 외 ' + (count - 1) + "권" : mainTitle;

    let orderNameInput = document.getElementById("order-name")
    orderNameInput.value = orderName;

    calculateSavePoint()
})





