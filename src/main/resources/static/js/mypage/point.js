document.addEventListener("DOMContentLoaded", function () {
    let linkList = document.querySelectorAll('.bold')

    for (var i = 0; i < linkList.length; i++) {
        let navLink = new URL(location.href).searchParams
        let param = navLink.get('type')
        if (param === null) {
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
    let value = document.getElementById("nickname");
    let myNick = document.getElementById('myNickname').value;
    let check = document.getElementById('check');

    if (value.value === myNick) {
        Swal.fire({
            icon: 'warning',
            text: '자기 자신에겐 선물할 수 없습니다'
        })
        value.value = '';
        return false;
    }

    $.ajax({
        type: "post",
        async: false,
        url: "/nickCheck",
        data: {"nickname": value.value},
        success: function (result) {
            if (result === true) {
                Swal.fire({
                    icon: 'success',
                    text: "선물 가능한 유저입니다. (닉네임 : " + value.value + ")"
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

    let pointReg = /^\d{1,500}$/;

    if (!pointReg.test(usePoint.value)) {
        Swal.fire({
            icon: 'warning',
            text: "포인트는 숫자만 입력 가능합니다"
        })
        usePoint.value = '';
        return;
    }

    if (parseInt(usePoint.value) > parseInt(userPoint.value)) {
        Swal.fire({
            icon: 'warning',
            text: '포인트가 부족합니다.'
        })
    } else if (parseInt(usePoint.value) <= 0) {
        Swal.fire({
            icon: 'warning',
            text: '0원 이상을 선물해주세요'
        })
    } else if (check.value === '0') {
        Swal.fire({
            icon: 'warning',
            text: '닉네임 확인 버튼을 눌러주세요'
        })
    } else {
        let loading = document.getElementById("loading");
        let img = document.getElementById("loading-img");
        loading.style.display = "flex";
        img.style.display = "block";

        let form = document.getElementById('gift-form')
        form.submit();
    }
}