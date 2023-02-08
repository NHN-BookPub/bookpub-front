function clickInfo(templateNo) {
    const memberNo = $('#memberNo').text();
    if (memberNo === '-1') {
        Swal.fire({
            icon: 'warning',
            title: '회원만 수령 가능합니다.',
            text: '로그인을 먼저 해주세요'
        })
    } else {
        $.ajax({
            type: "get",
            url: "/coupon/month-coupon",
            data: {"memberNo": memberNo, "templateNo": templateNo},
            success: function (result) {
                console.log(result);
                if (result === 3) {
                    Swal.fire({
                        icon: 'success',
                        title: '쿠폰이 발급되었습니다!',
                        text: '마이 쿠폰함을 확인해주세요.'
                    })
                } else if (result === 2) {
                    Swal.fire({
                        icon: 'warning',
                        title: '쿠폰이 소진되었습니다.',
                        text: '다음 기회에 참여해주세요.'
                    })

                } else {
                    Swal.fire({
                        icon: 'warning',
                        title: '이미 수령한 쿠폰입니다.',
                        text: '마이 쿠폰함을 확인해주세요.'
                    })
                }
            }

        })
    }
}

