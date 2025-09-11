dado = [    "https://upload.wikimedia.org/wikipedia/commons/1/1b/Dice-1-b.svg",
            "https://upload.wikimedia.org/wikipedia/commons/5/5a/Dice-2-b.svg",
            "https://upload.wikimedia.org/wikipedia/commons/b/b1/Dice-3-b.svg",
            "https://upload.wikimedia.org/wikipedia/commons/f/fd/Dice-4-b.svg",
            "https://upload.wikimedia.org/wikipedia/commons/0/08/Dice-5-b.svg",
            "https://upload.wikimedia.org/wikipedia/commons/2/26/Dice-6-b.svg"
        ];

        i = parseInt(Math.random() * 6);

        document.write(`<img src="${dado[i]}" alt="Dado ${i + 1}" width="30%">`);