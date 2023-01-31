let emptyReg = /\s/g;
let nameReg = /^.*(?=.*[가-힣a-z])(?=^.{2,200}).*$/;
let idReg = /^[a-z0-9_-]{5,20}$/;
let pwdReg = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$/
let nickReg = /^[a-zA-Z\\d]{2,8}$/;
let birthReg = /^\d{6}$/;
let phoneReg = /^.*(?=.*\d)(?=^.{11}).*$/;
let emailReg = /^\w+([\\.-]?\w+)*@\w+([\\.-]?\w+)*(\.\w{2,3})+$/;
const nicknameCheck = $("#nickname-check");
const idCheck = $("#id-check");

let authMessage;
let confirmBtn = $('#smsAuthConfirm');

window.addEventListener('load', () => {
    const forms = document.getElementsByClassName('validation-form');

    Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
            }

            if (check() === false) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        }, false);
    });
}, false);


function idPattern() {
    let idVal = document.getElementById('memberId').value;
    if (!idReg.test(idVal) || emptyReg.test(idVal)) {
        alert('아이디는 영어나 숫자로 5글자에서 20글자로 입력해주세요.')
        return false;
    }
    return true;
}

function nicknamePattern() {
    let nickVal = document.getElementById('nickname').value;
    if (!nickReg.test(nickVal) || emptyReg.test(nickVal)) {
        alert('닉네임은 영어나 숫자로 2글자 이상 8글자 이하로 입력해주세요.')
        return false;
    }
    return true;
}

function emailPattern() {
    let emailVal = document.getElementById('email').value;
    if (!emailReg.test(emailVal) || emptyReg.test(emailVal)) {
        alert('이메일 형식을 갖춰서 생성해주세요')
        return false;
    }
    return true;
}

function phonePattern() {
    let phoneVal = document.getElementById('phone').value;
    if (!phoneReg.test(phoneVal) || emptyReg.test(phoneVal)) {
        alert('전화번호 11자리를 입력해주세요 ( - 제외).')
        return false;
    }
    return true;
}

function check() {
    nicknamePattern();
    idPattern();
    emailPattern();
    phonePattern();

    let nameVal = document.getElementById('name').value;
    if (!nameReg.test(nameVal) || emptyReg.test(nameVal)) {
        alert('이름은 한글 2글자 이상으로 생성해주세요.')
        return false;
    }

    let birthVal = document.getElementById('birth').value;
    if (!birthReg.test(birthVal) || emptyReg.test(birthVal)) {
        alert('숫자로 이뤄진 생년월일 6자를 입력해주세요 (ex : 981008)')
        return false;
    }

    const pwdVal = document.getElementById('pwd').value;
    const pwdOkVal = document.getElementById('pwd-check').value;

    if (!pwdReg.test(pwdVal) || emptyReg.test(pwdVal)) {
        alert('비밀번호는 영대소문자,숫자,특수문자로 구성된 8글자 이상 20글자 이하로 생성하세요.')
        return false;
    } else {
        if (pwdOkVal) {
            if (pwdVal !== pwdOkVal) {
                alert('비밀번호가 일치하지 않습니다.')
                return false;
            }
        } else {
            alert('비밀번호확인을 입력하세요.')
            return false;
        }
    }

}


function idCheckFunc() {
    const id = $("#memberId").val();
    if (idPattern()) {
        $.ajax({
            type: "post",
            async: true,
            url: "/idCheck",
            data: {"id": id},
            success: function (result) {
                if (result === false) {
                    $('.id-ok').css("display", "inline-block");
                    $('.id-duplicate').css("display", "none");
                    idCheck.value = "1";
                    document.getElementById("memberId").readOnly = true;
                    if (allCheck()) {
                        $("#submitBtn").removeAttr("disabled");
                    }
                } else {
                    $('.id-duplicate').css("display", "inline-block");
                    $('.id-ok').css("display", "none");
                    alert("이미 사용하는 아이디 입니다.");
                    $('#memberId').val('');
                }
            },
        })
    } else {
        $('.id-duplicate').css("display", "inline-block");
        $('.id-ok').css("display", "none");
        $('#memberId').val('');
    }
}

function nickCheckFunc() {
    const nickname = $("#nickname").val();

    if (nicknamePattern()) {
        $.ajax({
            type: "post",
            async: true,
            url: "/nickCheck",
            data: {"nickname": nickname},
            success: function (result) {
                if (result === false) {
                    $('.nick-ok').css("display", "inline-block");
                    $('.nick-duplicate').css("display", "none");
                    nicknameCheck.value = "1";
                    document.getElementById("nickname").readOnly = true;
                    if (allCheck()) {
                        $("#submitBtn").removeAttr("disabled");
                    }
                } else {
                    $('.nick-duplicate').css("display", "inline-block");
                    $('.nick-ok').css("display", "none");
                    alert("이미 사용하는 닉네임 입니다.");
                    $('#nickname').val('');
                }
            },
        })
    } else {
        $('.nick-duplicate').css("display", "inline-block");
        $('.nick-ok').css("display", "none");
        $('#nickname').val('');
    }
}

function allCheck() {
    return idCheck.value === "1" && nicknameCheck.value === "1";
}


function smsAuth() {
    if (phonePattern()) {
        $.ajax({
            type: "post",
            async: false,
            url: "/smsSend",
            success: function (result) {
                alert("인증번호가 전송되었습니다");
                authMessage = result;
                console.log(authMessage)
                $('#smsAuthSend').css("display", "none");
                $('#smsAuthConfirm').css("display", "inline-block");
                $('#authInput').css("display", "inline-block");
                $('#inputLabel').css("display", "inline-block");
                document.getElementById("phone").readOnly = true;
            },
        })
    }
}

function smsConfirm() {
    const authInput = document.getElementById('authInput');
    if (authInput.value === authMessage) {
        alert("인증에 성공하였습니다.");
        authInput.disabled = true;
        confirmBtn.css("display", "none");
    } else {
        alert("인증에 실패하였습니다.")
        authInput.value = '';
    }
}

function findAddress() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.getElementById("address").value = data.roadAddress;
        }
    }).open();
}
