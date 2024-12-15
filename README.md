<h1>FlowPay - Fintech</h1>
<h2>O que é?</h2>
<p>
    Programa em Java para representar um sistema de uma empresa financeira de foma simples, 
    mas com foco em resolver um problema específico - a distribuição de solicitações de clientes em times específicos.
</p>
<h2>Times</h2>
<h3>1. Cartões</h3>
<p>Time destinado a resolver problemas com cartões.</p>
<h3>2. Empréstimos</h3>
<p>Time destinado a resolver problemas com empréstimos.</p>
<h3>3. Outros Assuntos</h3>
<p>Time destinado a resolver problemas relacionados a outros assuntos.</p>
<h2>Solução</h2>
<p>
    Pensei em desenvolver um programa que rode em terminal para executar essa transferência de solicitações.
    <br>
    A solução não utiliza nenhuma API e os dados não são persistidos em banco de dados.
    Portanto, ao parar a execução do programa os dados são perdidos e é necessário realizar o processo novamente.
    <br>
    O foco foi aplicar conceitos de Clean Code e de testes, mas de forma a manter a mira no problema principal.
</p>
<h2>Como funciona?</h2>
<p>
    O sistema possui duas formas de entrada, como cliente e como ADMIN do sistema.
    <br />
    Clientes podem abrir chamados sobre algum problema específico. Então, esses chamados serão transferidos a fila de 
    algum time.
    <br />
    . Caso o time não possua nenhum atendente, a solicitação aberta irá para a fila geral do time.
    <br />
    . Caso o time possua atendente, mas este esteja ocupado, a solicitação também irá para a fila geral do time.
    <br />
    . Caso o time possua atendente e este não esteja ocupado, a solicitação não cairá na fila geral, ou seja, irá direto
    para a lista de atendimentos do atendente disponível.
</p>
<h2>Stack Utilizada</h2>
<p>. Java</p>
<p>. JUnit para testes</p>
