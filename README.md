# To-Do List
## Visão Geral
 Uma API de gerencimento de projetos que visa simplificar a organização e o acompanhamento de tarefas e projetos com funcionalidades como:
 
- Manter Projeto
- Manter Participantes do Projeto
- Manter Sprints
- Manter Tarefas
- Manter Status (*lanes*)

O intuito principal do projeto é estudar java e springboot.
## Documento de Requisitos

Aproveitando e reforçando o que foi aprendido na matéria de Engenharia de Software me arrisquei a criar um [documento de requisitos](https://docs.google.com/document/d/1WkRMbIov44BUu5Hdrc_sfs9cDKm2S-_mAXnKg2RI9HM/edit?usp=sharing).

Você também pode consultar o [Diagrama de Classes](https://lucid.app/lucidchart/ee2028eb-7917-4db6-a524-8f75662b5c1b/edit?page=HWEp-vi-RSFO&invitationId=inv_f22d80f0-f0c2-4465-9802-724ad581fb71#).

## TO:DO

 - [ ] Aumentar a cobertura de testes
 - [ ] Adicionar Paginação aos endpoints de listagem
 - [ ] Implementar Autenticação com AccessToken e RefreshToken
 - [ ] Isolar as variáveis de configuração
 - [ ] Configurar um banco de testes H2 
 - [x] Conteinerizar

## Instalação

1. Clone o repositório:


    `git clone https://github.com/itallopacheco/To-do-list.git`

	
2. Acesse o diretório 



    `cd to-do-list`


3. Inicie o Docker



    `docker compose up`

4. Teste a aplicação



    `http://localhost:8080/api/ping`


