let interval;
let callCount = 0;
const memberNo = $('#memberNo').text();
function clickInfo(templateNo) {

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
        $.ajax({
            type: "get",
            url: "/coupon/month-coupon",
            data: {"memberNo": memberNo, "templateNo": templateNo},
            success: function (result) {
                if(result === "OK"){
                    interval = setInterval(function () { confirm(templateNo); },5000);
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
            if(callCount == 3){
                console.log("call count 초과");
                clearInterval(interval);
                Swal.fire({
                    icon: 'warning',
                    title: '시간이 초과되었습니다..',
                    text: '다시 시도해주세요.'
                })
            }
            else if(result === true){
                console.log("true 반환");
                clearInterval(interval);
                Swal.fire({
                    icon: 'success',
                    title: '쿠폰이 발급되었습니다!',
                    text: '마이 쿠폰함을 확인해주세요.'
                })
            }
        }

    })

}


