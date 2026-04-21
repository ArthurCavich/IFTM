import './Cabecalho.css';

export default function Cabecalho({ titulo, subtitulo }) {
    return (
        <section className="cabecalho">
            <h2>{titulo}</h2>
            <p>{subtitulo}</p>
        </section>
    );
}
