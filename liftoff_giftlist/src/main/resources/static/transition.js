window.onload = () => {
    const transition_el = document.querySelector('.transition');
    const links = document.querySelectorAll('a');

    setTimeout(() => {
    transition_el.classList.remove('is-active');
    }, 500);

    for (let i = 0; i < links.length; i++){
        let link = links[i];

        link.addEventListener('click', e => {
            e.preventDefault();
            let target = e.target.href;

            transition_el.classList.add('is-active');

            setTimeout(() => {
                window.location.href = target;
                }, 500);

        })

    }

};