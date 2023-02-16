var exPwd = document.getElementById("exchangePwd");
var checkPwd = document.getElementById("exchangeCheckPwd");
let nickReg = /^[a-zA-Z\d]{2,8}$/;
let emptyReg = /\s/g;

function validatePassword() {
    if (exPwd.value === checkPwd.value) {
        document.getElementById("pwd-register")
            .disabled = false;
        checkPwd.checkValidity(true);
        checkPwd.setCustomValidity('');

    } else {
        checkPwd.setCustomValidity("패스워드가 일치하지 않습니다.");
        checkPwd.disabled = false;
    }
}

function phonePattern() {
    let phoneVal = document.getElementById('exchangePhone').value;
    if (isNaN(parseInt(phoneVal))) {
        alert('전화번호를 입력해주세요')
        return false;
    }

    if (emptyReg.test(phoneVal)) {
        alert('전화번호에는 공백이 있을 수 없습니다.')
        return false;
    }

    console.log(parseInt(phoneVal));

    if (parseInt(phoneVal) < 1000000000 || parseInt(phoneVal) >= 1100000000) {
        alert('유효한 전화번호 11자리를 입력해주세요 ( - 제외).')
        return false;
    }
    return true;
}

function smsAuth() {
    if (phonePattern()) {

        $.ajax({
            type: "post",
            async: false,
            url: "/smsSend",
            success: function (result) {
                alert("인증번호가 전송되었습니다.");
                authMessage = result;
                $('#smsAuthSend').css("display", "none");
                $('#smsAuthConfirm').css("display", "inline-block");
                $('#authInput').css("display", "inline-block");
            }
        })
    }
}


function smsConfirm() {
    const phone = document.getElementById('exchangePhone');
    const authInput = document.getElementById('authInput');
    if (authInput.value === authMessage) {
        alert("인증에 성공하였습니다.");
        phone.readable = true;
        authInput.disabled = true;
        $('#smsAuthConfirm').css("display", "none");
        document.getElementById("phone-auth")
            .disabled = false;
        $('#authInput').css("disabled", 'true');
    } else {
        alert("인증에 실패하였습니다.")
        authInput.value = '';
    }
}


function pwdCheck() {
    const rawPwd = document.getElementById("currentPwd").value;
    $.ajax({
        type: "post",
        async: false,
        dataType: 'json',
        data: {rawPassword: rawPwd},
        url: "/members/password-check"
        , success: function (result) {
            if (result === true) {
                alert("성공했습니다.")
                document.getElementById("exchangePwd")
                    .disabled = false;
                document.getElementById("exchangeCheckPwd")
                    .disabled = false;
                document.getElementById("currentPwd")
                    .disabled = true;
                document.getElementById("check-current-pwd")
                    .disabled = true;
            } else {
                alert("비밀번호가 맞지않습니다.")
            }
        }
    })
}

function nicknamePattern() {
    let nickVal = document.getElementById("nickname").value;
    if (!nickReg.test(nickVal) || emptyReg.test(nickVal)) {
        alert('닉네임은 영어나 숫자로 2글자 이상 8글자 이하로 입력해주세요.')
        return false;
    }
    return true;
}

function nickCheckFunc() {
    let value = document.getElementById("nickname").value;
    if (nicknamePattern()) {
        $.ajax({
            type: "post",
            async: true,
            url: "/nickCheck",
            data: {"nickname": value},
            success: function (result) {
                if (result === false) {
                    document.getElementById("nick-submit")
                        .disabled = false;
                    document.getElementById("nickname")
                        .disabled = true;

                    alert("이용가능한 닉네임 입니다.");
                } else {
                    alert("이미 사용하는 닉네임 입니다.");
                    document.getElementById("nickname")
                        .value = '';
                }
            }
        })
    }
}

function findAddress() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.getElementById("roadAddress").value = data.roadAddress;
        }
    }).open();
    document.getElementById("address-detail").disabled = false;
}

var address = document.getElementById("roadAddress").value;

function checkAddress(obj) {
    if (obj.value !== null) {
        document.getElementById("addressCheck").disabled = false;
    } else document.getElementById("addressCheck").disabled = true;
}