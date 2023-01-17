let emptyReg = /\s/g;
let nameReg = /^.*(?=.*[가-힣a-z])(?=^.{2,200}).*$/;
let idReg = /^.*(?=.*[a-z])(?=.*\d)(?=^.{5,20}).*$/;
let pwdReg = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$/
let nickReg = /^.*(?=.*[a-z])(?=.*[a-z\d])(?=.{2,8}).*$/;
let birthReg = /^.*(?=.*\d)(?=^.{6}).*$/;
let phoneReg = /^.*(?=.*\d)(?=^.{11}).*$/;
let emailReg = /^\w+([\\.-]?\w+)*@\w+([\\.-]?\w+)*(\.\w{2,3})+$/;
const nicknameCheck = $("#nickname-check");
const idCheck = $("#id-check");
const emailCheck = $("#email-check");

let authMessage;
let authText = $('#authInput');
let authButton = $('#smsAuthConfirm');
let authBtn = $('#smsAuthSend');


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
        alert('아이디는 영소문자,숫자로 구성된 5글자 이상, 20글자 이하로 생성해주세요.')
        return false;
    }
    return true;
}

function nicknamePattern() {
    let nickVal = document.getElementById('nickname').value;
    if (!nickReg.test(nickVal) || emptyReg.test(nickVal)) {
        alert('닉네임은 영소문자는 필수, 숫자는 선택으로 2글자 이상, 8글자 이하로 생성해주세요.')
        return false;
    }
    return true;
}

function emailPatter() {
    let emailVal = document.getElementById('email').value;
    if (!emailReg.test(emailVal) || emptyReg.test(emailVal)) {
        alert('이메일 형식을 갖춰서 생성해주세요')
        return false;
    }
    return true;
}

function check() {
    nicknamePattern();
    idPattern();
    emailPatter();

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

    let phoneVal = document.getElementById('phone').value;
    if (!phoneReg.test(phoneVal) || emptyReg.test(phoneVal)) {
        alert('전화번호 11자리를 입력해주세요 ( - 제외).')
        return false;
    }
}


function idCheckFunc() {
    const id = $("#memberId").val();

    $.ajax({
        type: "post",
        async: true,
        url: "./idCheck",
        data: {"id": id},
        success: function (result) {
            if (result === false && idPattern()) {
                $('.id-ok').css("display", "inline-block");
                $('.id-duplicate').css("display", "none");
                idCheck.value = "1";
                if (allCheck()) {
                    $("#submitBtn").removeAttr("disabled");
                }
            } else {
                $('.id-duplicate').css("display", "inline-block");
                $('.id_ok').css("display", "none");
                alert("이미 사용하는 아이디 입니다.");
                $('#memberId').val('');
            }
        },
    })
}

function emailCheckFunc() {
    const email = $("#email").val();

    $.ajax({
        type: "post",
        async: true,
        url: "./emailCheck",
        data: {"email": email},
        success: function (result) {
            if (result === false && emailPatter()) {
                $('.email-ok').css("display", "inline-block");
                $('.email-duplicate').css("display", "none");
                emailCheck.value = "1";
                if (allCheck()) {
                    $("#submitBtn").removeAttr("disabled");
                }
            } else {
                $('.email-duplicate').css("display", "inline-block");
                $('.email_ok').css("display", "none");
                alert("이미 사용하는 이메일입니다.");
                $('#email').val('');
            }
        },
    })
}

function nickCheckFunc() {
    const nickname = $("#nickname").val();

    $.ajax({
        type: "post",
        async: true,
        url: "./nickCheck",
        data: {"nickname": nickname},
        success: function (result) {
            if (result === false && nicknamePattern()) {
                $('.nick-ok').css("display", "inline-block");
                $('.nick-duplicate').css("display", "none");
                nicknameCheck.value = "1";
                if (allCheck()) {
                    $("#submitBtn").removeAttr("disabled");
                }
            } else {
                $('.nick-duplicate').css("display", "inline-block");
                $('.nick_ok').css("display", "none");
                alert("이미 사용하는 닉네임 입니다.");
                $('#nickname').val('');
            }
        },
    })
}

function allCheck() {
    return idCheck.value === "1" && emailCheck.value === "1" && nicknameCheck.value === "1";
}


function smsAuth() {

    $.ajax({
        type: "post",
        async: true,
        url: "./smsSend",
        success: function (result) {
            authMessage = result;
            authBtn.css("display", "none");
            authText.css("display", "inline-block");
            authButton.css("display", "inline-block");
        },
    })
}

function smsConfirm() {
    console.log(authMessage);
    console.log(authText.val());

    if (authText.val() === authMessage) {
        alert("인증에 성공하였습니다")
        authText.css("display", "none");
        authButton.css("display", "none");

    } else {
        authText.val('');
    }
}