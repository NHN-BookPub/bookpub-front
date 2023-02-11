const Editor = toastui.Editor;

const viewer = new Editor({
    el: document.querySelector('#viewer'),
    viewer: true
});

let question = document.getElementById('question-hidden');
viewer.setMarkdown(question.textContent);


