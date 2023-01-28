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

    for (var i = 1; i < $('table tr').size(); i++) {
        const chk = $('table tr').eq(i).children().find('input[type="checkbox"]').is(':checked');

        if (chk === true) {
            const cnt = $('table tr').eq(i).find('input[type="number"]').val();
            productCnt.push(cnt);
        }
    }

    const checkBox = $("input[name=product]:checked");

    checkBox.each(function (i) {
        const tr = checkBox.parent().parent().eq(i);
        const td = tr.children();

        productArr.push(td.eq(1).children().eq(0).text())
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
            window.location = '/orders';
        }
    })
}