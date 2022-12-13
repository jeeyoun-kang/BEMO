// 로그인 popup 관련 script
function show () {
    document.querySelector(".background").className = "background show";
}

function close () {
    document.querySelector(".background").className = "background";
}

document.querySelector('#show').addEventListener('click', show);
document.querySelector('#close').addEventListener('click', close);


// // 네이버 로그인 api 관련 script
// const naverLogin = new naver.LoginWithNaverId(
//     {
//         clientId: "bewHk2sJKKeHUvdyVRII", //내 애플리케이션 정보에 cliendId를 입력해줍니다.
//         callbackUrl: "http://localhost:8080/", // 내 애플리케이션 API설정의 Callback URL 을 입력해줍니다.
//         isPopup: false,
//         callbackHandle: true,
//         loginButton: {color: "green", type: 3, height: 60}
//     }
// );
// naverLogin.init(); // 로그인 설정
//
// window.addEventListener('load', function () {
//     // 이름, 이메일 주소, 별명, 출생연도, 휴대전화번호 + 생일
//     // 메가박스는 이름, 이메일 주소 추가만
//     naverLogin.getLoginStatus(function (status) {
//         if (status) {
//             const email = naverLogin.user.getEmail(); // 필수로 설정할것을 받아와 아래처럼 조건문을 줍니다.
//
//             console.log(naverLogin.user);
//         } else {
//             console.log("callback 처리에 실패하였습니다.");
//         }
//     });
// });
//
//
// let testPopUp;
//
// function openPopUp() {
//     testPopUp= window.open("https://nid.naver.com/nidlogin.logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
// }
// function closePopUp(){
//     testPopUp.close();
// }
//
// function naverLogout() {
//     openPopUp();
//     setTimeout(function() {
//         closePopUp();
//     }, 1000);
// }