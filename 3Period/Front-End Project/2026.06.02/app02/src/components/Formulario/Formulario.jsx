import React, { useState } from 'react'
import style from './Formulario.module.css'
import emailjs from '@emailjs/browser';

export default function Formulario() {

    const [form, setForm] = useState({ nome: '', email: '', msg: '' });

    const [msgErro, setMsgErro] = useState("");

    function alterarForm(e) {
        setMsgErro("")
        setForm({ ...form, [e.target.name]: e.target.value });
    }

    function validar() {
        if (form.nome == "" || form.email == "" || form.msg == "") {
            setMsgErro("Todos os campos devem ser preenchidos!")
        } else {
            var templateParams = {
                from_name: form.nome,
                from_email: form.email,
                from_msg: form.msg
            };

            emailjs.send('service_hyuenvg', 'template_ojci3zi', templateParams, 'MFBCIWpoIEEiLDpeZ').then(
                (response) => {
                    setMsgErro("E-mail enviado com sucesso!")
                },
                (error) => {
                    setMsgErro("Não foi possível enviar o e-mail!!")
                },
            );
        }
    }

    return (
        <>
            <form>
                <label htmlFor="">Nome:</label>
                <input type="text" name='nome' onChange={alterarForm} />

                <label htmlFor="">E-mail:</label>
                <input type="text" name='email' onChange={alterarForm} />

                <label htmlFor="">Mensagem:</label>
                <input type="textarea" name='msg' onChange={alterarForm} />
                <br></br>
                <input type="button" value="Enviar" onClick={validar} />

                <p>{msgErro}</p>
            </form>

        </>
    )
}
