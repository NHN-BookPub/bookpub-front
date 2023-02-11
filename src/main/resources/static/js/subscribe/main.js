function cal() {
    const price = document.getElementById("addPrice").value;
    const rate = document.getElementById("addSaleRate").value;
    if (rate != null && price != null) {
        var number = price * (rate / 100);

        document.getElementById("addSalePrice").value = price - number;
        document.getElementById("submitSubscribe").disabled = false;
        $('input[name=salePrice]').attr('value', price - number);
    } else {
        document.getElementById("addSalePrice").value = null;
    }

}