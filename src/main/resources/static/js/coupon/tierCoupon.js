function clickCouponButton(number) {
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
      url: "/members/member",
      data: {"memberNo": memberNo},
      success: function (tierNo) {
        if (number !== tierNo) {
          Swal.fire({
            icon: 'warning',
            title: '해당 등급만 수령가능합니다.',
            text: '등급을 확인해주세요.'
          })
        } else {
          $.ajax({
            type: "get",
            url: "/coupon/tier-coupon",
            data: {"memberNo": memberNo, "tierNo": tierNo},
            success: function (result) {
              if (result === true) {
                Swal.fire({
                  icon: 'success',
                  title: '쿠폰이 발급되었습니다!',
                  text: '마이 쿠폰함을 확인해주세요.'
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
    })
  }

}