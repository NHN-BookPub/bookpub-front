function cancelLike(productNo) {
    const memberNo = $('#memberNo').text();

    Swal.fire({
        title: '좋아요를 취소하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '확인',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                icon: 'success',
                title: '삭제가 완료되었습니다.',
                text: '즐거운 쇼핑 되세요~~',
            })
            $.ajax({
                type: "delete",
                url: "/wishlist",
                async: false,
                dataType: 'json',
                data: {"memberNo": memberNo, "productNo": productNo}
            })

            location.reload();
        }
    })
}

function alarm(productNo) {
    const memberNo = $('#memberNo').text();

    Swal.fire({
        title: '알람상태를 변경하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '확인',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                timer: 10000,
                icon: 'success',
                title: '변경되었습니다',
                text: '즐거운 쇼핑 되세요~',
                showConfirmButton: false
            })
            $.ajax({
                type: "put",
                url: "/wishlist",
                async: false,
                dataType: 'json',
                data: {"memberNo": memberNo, "productNo": productNo}
            })

            location.reload();
        }
    })
}