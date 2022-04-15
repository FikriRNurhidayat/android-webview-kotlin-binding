// Fikri Rahmat Nurhidayat
// https://microsoft.github.io/monaco-editor/api/modules/monaco.editor.html

const initialCode = `
function addTwoNumber(a, b) {
    return a + b;
}
`
var editor = monaco.editor.create(document.getElementById('container'), {
    value: initialCode,
    minimap: {
        enabled: false,
    },
    theme: 'vs-dark',
    scrollbar: {
        vertical: "hidden",
        horizontal: "hidden",
    },
    language: 'javascript'
});

editor.onDidChangeModelContent(handleTextChange)

// Set initial value
handleTextChange(initialCode)

function handleTextChange(e) {
    Android.handleTextChange(editor.getValue())
}