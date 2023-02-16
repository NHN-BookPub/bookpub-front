function inquiry(productNo) {
    window.location.href = '/members/inquiryForm/products/' + productNo;
}
function modal(event, orderNum, orderProductNum) {
    let modal1 = document.getElementById("refund");

    event.stopPropagation();

    let refund = document.getElementById('refund');
    refund.innerHTML = '';

    let modalDiv = document.createElement('div');
    modalDiv.classList.add('modal_box');
    modalDiv.innerHTML = `<h2>환불</h2>
            <form action="/payment/order-product/refund" method="post" style="width: 100%;">
            <table style="width: 100%">
                <tbody>
               <tr>
                    <th scope="row" style="padding: 10px 20px;" class="has_ip">환불사유</th>
                </tr>
                <tr>
                    <input type="hidden" name="orderNo" value="${orderNum}">
                    <input type="hidden" name="orderProductNo" value="${orderProductNum}">
                    <td style="padding: 0 1rem 1rem 1rem;">
                        <textarea id="refund-text"
                                  name="cancelReason"
                                  placeholder="환불 사유를 입력해주세요"></textarea>
                    </td>
                </tr>
                </tbody>
            </table>
            <div style="display: flex;justify-content: flex-end;padding: 0 1rem 0 0;">
                <button type="submit" id="click"
                        style="background-color: white;border: 1px solid #888888;border-radius: 10px;">환불신청
                </button>
            </div>
        </form>`;

    refund.appendChild(modalDiv);
    modal1.style.display = "flex";
}

function modalOpen(event, orderNum, orderProductNum) {
    let modal2 = document.getElementById("exchange");
    event.stopPropagation();

    let exchange = document.getElementById('exchange');
    exchange.innerHTML = '';

    let modalDiv = document.createElement('div');
    modalDiv.classList.add('modal_box');
    modalDiv.innerHTML = `<h2>교환</h2>
            <form action="/payment/order-product/exchange" method="post" style="width: 100%;">
            <table style="width: 100%">
                <tbody>
               <tr>
                    <th scope="row" style="padding: 10px 20px;" class="has_ip">교환사유</th>
                </tr>
                <tr>
                    <input type="hidden" name="orderNo" value="${orderNum}">
                    <input type="hidden" name="orderProductNo" value="${orderProductNum}">
                    <td style="padding: 0 1rem 1rem 1rem;">
                        <textarea id="refund-text"
                                  name="cancelReason"
                                  placeholder="교환 사유를 입력해주세요"></textarea>
                    </td>
                </tr>
                </tbody>
            </table>
            <div style="display: flex;justify-content: flex-end;padding: 0 1rem 0 0;">
                <button type="submit" id="click"
                        style="background-color: white;border: 1px solid #888888;border-radius: 10px;">교환신청
                </button>
            </div>
        </form>`;

    exchange.appendChild(modalDiv);
    modal2.style.display = "flex";
}
