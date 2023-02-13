document.addEventListener("DOMContentLoaded", function () {
    let linkList = document.querySelectorAll('.bold')

    for (var i = 0; i < linkList.length; i++) {
        let navLink = new URL(location.href).searchParams
        let param = navLink.get('type')
        if (param === '') {
            if (linkList[i].value === '1') {
                linkList[i].style.fontWeight = 'bold';
                linkList[i].style.color = 'black';
                break;
            }
        }
        if (linkList[i].value === param) {
            linkList[i].style.fontWeight = 'bold';
            linkList[i].style.color = 'black';
        }
    }
});

function nickCheckFunc() {
    let value = document.getElementById("nickname").value;
    let check = document.getElementById('check');
    $.ajax({
        type: "post",
        async: true,
        url: "/nickCheck",
        data: {"nickname": value},
        success: function (result) {
            if (result === true) {
                Swal.fire({
                    icon: 'success',
                    text: "선물 가능한 유저입니다. (닉네임 : " + value + ")"
                })
                check.value = '1';
            } else {
                Swal.fire({
                    icon: 'warning',
                    text: '존재하지 않는 유저입니다 닉네임을 체크해주세요.'
                })
                document.getElementById("nickname").value = '';
            }
        }
    })
}

function pointGift() {
    let usePoint = document.getElementById('point-amount')
    let userPoint = document.getElementById('hold-point')
    let check = document.getElementById('check');

    if (parseInt(usePoint.value) > parseInt(userPoint.value)) {
        Swal.fire({
            icon: 'warning',
            text: '포인트가 부족합니다.'
        })
    } else if (check.value === '0') {
        Swal.fire({
            icon: 'warning',
            text: '닉네임 확인 버튼을 눌러주세요'
        })
    } else {
        let form = document.getElementById('gift-form')
        form.submit();
    }
}