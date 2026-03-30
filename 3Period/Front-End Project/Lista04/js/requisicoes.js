async function obterMaisPopulares(pagina = 1) {
    //url + parâmetros pra requisição com bearer
    const url = `https://api.themoviedb.org/3/movie/popular?language=pt-BR&page=${pagina}`;
    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMWY2NDVlZjE5MThjZDVhYmRlMTZlNmEwYTVmZDc2MiIsIm5iZiI6MTc3NDcxMzA2Mi43NCwic3ViIjoiNjljN2Y4ZTY2MmZkZWYzZDQ4MDc0NzRkIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.ufXD22pD9qdBjbqhppRjPlJLXHPH5A6KE7rCW2nSqU0'
        }
    };

    try {
        const resposta = await fetch(url, options);
        const dados = await resposta.json();
        return dados.results;
    }
    catch (erro) {
        console.log(erro.message);
        return []; // o programa continua funcionando em caso de erro.
    }
}

async function obterGeneros() {
    const url = 'https://api.themoviedb.org/3/genre/movie/list?language=pt-BR';
    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMWY2NDVlZjE5MThjZDVhYmRlMTZlNmEwYTVmZDc2MiIsIm5iZiI6MTc3NDcxMzA2Mi43NCwic3ViIjoiNjljN2Y4ZTY2MmZkZWYzZDQ4MDc0NzRkIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.ufXD22pD9qdBjbqhppRjPlJLXHPH5A6KE7rCW2nSqU0'
        }
    };

    try {
        const resposta = await fetch(url, options);
        const dados = await resposta.json();
        return dados.genres;
    }
    catch (erro) {
        console.log(erro.message);
        return []; //o programa continua funcionando em caso de erro.
    }
}

export { obterMaisPopulares, obterGeneros };