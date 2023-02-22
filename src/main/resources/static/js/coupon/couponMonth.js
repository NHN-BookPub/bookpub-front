let interval;
let callCount;
const memberNo = $('#memberNo').text();

let couponList = document.getElementsByClassName("templateGroup");
let couponValueList = [];

window.onload = function () {
    for(var i=0; i<couponList.length; i++) {
        couponValueList.push(couponList[i].value);
    }

    if(memberNo >= 0) {
        $.ajax({
            type: "get",
            url: "/token/coupons/" + memberNo +"/month-coupons/issue-check",
            traditional: true,
            data: {"templateList": couponValueList},
            success: function (result) {
                result.forEach((couponElement, index) => {
                    if (couponElement === false) {
                        console.log(document.getElementById("couponBtn" + couponValueList[index]));
                        document.getElementById("couponBtn"+couponValueList[index]).classList.remove("disabled");
                    }
                })
            }
        })
    }
    else {
        for(var j=0; j<couponValueList.length; j++) {
            document.getElementById("couponBtn"+couponValueList[j]).classList.remove("disabled");
        }
    }
}

function clickInfo(templateNo) {
    callCount = 0;

    var openedAt = document.getElementById(templateNo).textContent;
    openedAt = new Date(openedAt);

    const current = new Date();

    if (memberNo === '-1') {
        Swal.fire({
            icon: 'warning',
            title: '회원만 수령 가능합니다.',
            text: '로그인을 먼저 해주세요'
        })
    } else if (openedAt > current) {
        Swal.fire({
            icon: 'warning',
            title: '오픈 시간을 확인 해주세요',
            text: '오픈 시간 : ' + openedAt.toUTCString()
        })
    } else {
        let loading = document.getElementById("loading");
        let img = document.getElementById("loading-img");
        loading.style.display = "flex";
        img.style.display = "block";

        $.ajax({
            type: "get",
            url: "/coupon/month-coupon",
            data: {"memberNo": memberNo, "templateNo": templateNo},
            success: function (result) {
                if(result === "OK"){
                    interval = setInterval(function () { confirm(templateNo); },1500);
                }
                else{
                    Swal.fire({
                        icon: 'warning',
                        title: '수량이 모두 소진되었습니다.',
                        text: '다음 기회에 도전해보세요.'
                    })
                    loading.style.display = "none";
                }
            }
        })
    }
}

function confirm(templateNo){
    callCount++;
    $.ajax({
        type:"get",
        url:"/coupon/month-coupon/check-issued",
        data: {"memberNo": memberNo, "templateNo": templateNo},
        success: function (result){
            if(callCount >= 6){
                clearInterval(interval);
                loading.style.display = "none";
                Swal.fire({
                    icon: 'warning',
                    title: '시간이 초과되었습니다..',
                    text: '다시 시도해주세요.'
                })
            }
            else if(result === true){
                clearInterval(interval);
                loading.style.display = "none";
                Swal.fire({
                    icon: 'success',
                    title: '쿠폰이 발급되었습니다!',
                    text: '마이 쿠폰함을 확인해주세요.'
                })
                document.getElementById("couponBtn"+templateNo).classList.add("disabled");
            }
        }
    })
}


