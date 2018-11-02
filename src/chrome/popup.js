"use strict";

const noteAddUrl = "http://localhost:82/add-note"

document.getElementById("save_button").addEventListener("click", () => {
    let form = new FormData(document.querySelector('form'))

    var data = {}
    data.status = form.get("status")
    data.note = form.get("note")

    chrome.tabs.query({
        active: true,
        currentWindow: true
    }, ([currentTab]) => {
        data.url = currentTab.url
        data.title = currentTab.title

        fetch(noteAddUrl, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
            .then(response => {
                if (response.ok) {
                    document.getElementById("result").innerText = "Saved"
                } else {
                    throw `${response.status} ${response.statusText}`
                }
            })
            .catch(error => {
                document.getElementById("result").innerText = `Error: ${error}`
            })
    });
})