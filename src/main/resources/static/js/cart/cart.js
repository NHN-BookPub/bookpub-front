function selectAll(selectAll) {
    const checkboxes
        = document.getElementsByName('product');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })
}

function order() {
    const productArr = [];
    const productCnt = [];

    const checkBox = $("input[name=product]:checked");

    checkBox.each(function (i) {
        const tr = checkBox.parent().parent().eq(i);
        const td = tr.children();

        productArr.push(td.eq(1).children().eq(0).text())
        productCnt.push(td.eq(3).children().eq(0).val())
    });


    if (productArr.length === 0) {
        $("#txt_getChkList").val("");
        Swal.fire({
            icon: 'warning',
            title: '선택된 상품이 없습니다.',
            text: '상품을 선택하세요',
        });
        return;
    }

    const result = [];

    for (let i = 0; i < productArr.length; i++) {
        result.push({"productNo": productArr[i], "count": productCnt[i]})
    }

    var cart = JSON.stringify(result);

    $.ajax({
        type: "post",
        url: "/cart/order",
        async: true,
        traditional: true,
        data: {"cart": cart},
        dataType: "json",

        success: function () {
            alert('결제하러가기')
            window.location = '/orders/order';
        }
    })
}

function totalPrice(count, product) {
    let countInput = document.getElementById('num' + product.productNo);

    if (!Number.isInteger(count)) {
        count = Math.ceil(count);
        countInput.value = count;
    }

    if (count <= 0) {
        count = 1;
        countInput.value = count;
    }

    if (count > product.productStock) {
        alert("해당 상품의 재고는 " + product.productStock + "개 입니다")
        count = product.productStock;
        countInput.value = count;
    }

    let totalPrice = document.getElementById('totalPrice' + product.productNo)
    let originPrice = document.getElementById('originPrice' + product.productNo)

    totalPrice.innerText = parseKRW((parseInt(parsingNumber(originPrice.innerText)) * count).toString() + '원');
}


function parseKRW(data) {
    return data.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
}

function parsingNumber(data) {
    return data.replaceAll(",", "")
}