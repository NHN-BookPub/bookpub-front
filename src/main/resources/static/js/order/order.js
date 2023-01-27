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
    let userPoint = document.getElementById("userPoint").innerText;
    let usePoint = document.getElementById("tbl-point")

    if (parseInt(userPoint) < usePoint.value) {
        alert("포인트가 부족합니다.")
        usePoint.value = "";
    } else {
        alert("포인트를 사용합니다.")
        document.getElementById("usePoint").innerText = usePoint.value
    }
}