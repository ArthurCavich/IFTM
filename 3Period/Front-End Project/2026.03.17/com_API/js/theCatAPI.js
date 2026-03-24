//async usado pra realizar a requisição assíncrona, eu não sei quanto tempo vai levar.

const getCats = async () => {
    const url = "https://api.thecatapi.com/v1/images/search?limit=10&breed_ids=pers,sphy,siam,abys&api_key=live_nT9nHlyF3pgRfvBYEiYR1jlALimSD2MNmR31Gnj93YEoYm7jvScdxnZpBh985IBG";
    //precisa esperar a resposta pq depois de carregar ele vai fazer os .then...
    try {
        const resposta = await fetch(url)
        const gatos = await resposta.json(); //aqui retorna a requisição e passo para outra variável e processo em json() para manipular.
        return gatos;
    }
    catch (error) {
        console.log(error.message);
        return [];
    }

};

export default getCats;