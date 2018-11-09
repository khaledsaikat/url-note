'use strict';

const apiUrl = "http://localhost:82/get-notes"

chrome.tabs.onUpdated.addListener(function(tabId, changeInfo, tab) {
    if (changeInfo.status == 'complete' && tab.active) {
        let url = apiUrl + "?url=" + tab.url;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw response.statusText
                }
                return response.json()
            })
            .then(response => {
                var result = ""
                response.forEach(note => {
                    result += `\n${note.status} ${note.note}`
                });

                alert(result)
            })
            .catch(error => console.log(error))
    }
});