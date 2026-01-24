# CombustionCarApp
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-7F52FF?style=for-the-badge&logo=kotlin)
![Android](https://img.shields.io/badge/Android-Studio-3DDC84?style=for-the-badge&logo=android-studio)

## 📖 Sobre o Projeto

**CombustionCarApp** é um aplicativo Android desenvolvido como projeto prático durante o bootcamp **Mobile Developer** da [Cognizant](https://www.cognizant.com/br/pt) em parceria com a [DIO (Digital Innovation One)](https://www.dio.me/).

O objetivo do aplicativo é listar informações sobre carros a partir de uma API remota, permitir o cálculo de autonomia de combustível e possibilitar que o usuário favorite seus carros preferidos, salvando esses dados localmente no dispositivo.

## 📸 Screenshots

| Tela Principal (Lista de Carros) | Tela de Favoritos | Tela de Cálculo de Autonomia |
| :------------------------------: | :---------------: | :--------------------------: |
| <img width="220" height="1600" alt="Screenshot_20260124_030340" src="https://github.com/user-attachments/assets/2f0247da-403a-4669-8ee6-4d2667ff41db" />|<img width="220" height="1600" alt="Screenshot_20260124_030428" src="https://github.com/user-attachments/assets/03d9b2f7-cf69-4dde-97aa-2e69dcdd21aa" />| <img width="220" height="1600" alt="Screenshot_20260124_030506" src="https://github.com/user-attachments/assets/7cf9577a-b4e5-4c92-b889-8733a742af67" />|

## ✨ Funcionalidades

-   [x] **Listagem de Carros**: Exibe uma lista de carros obtida de uma API REST.
-   [x] **Navegação por Abas**: Interface organizada com `TabLayout` para separar a lista geral da lista de favoritos.
-   [x] **Cálculo de Autonomia**: Tela dedicada para calcular a autonomia de um veículo com base no preço do combustível e na distância a ser percorrida.
-   [x] **Sistema de Favoritos**:
    -   Permite ao usuário marcar e desmarcar carros como favoritos.
    -   Persistência local dos carros favoritados usando um banco de dados **SQLite**.
    -   Exibição dos carros favoritados em uma tela separada.
-   [x] **Feedback Visual**: Exibe um indicador de progresso durante o carregamento dos dados e um estado de erro em caso de falha de conexão com a internet.

## 🛠️ Tecnologias e Arquitetura

Este projeto foi construído utilizando as seguintes tecnologias e conceitos do ecossistema Android:

-   **Linguagem:** [Kotlin](https://kotlinlang.org/)
-   **Arquitetura:**
    -   **Repository Pattern**: Camada de abstração para isolar as fontes de dados (rede e banco de dados local) do resto do aplicativo.
-   **Interface do Usuário (UI):**
    -   **Android Views (XML)**: Construção declarativa das interfaces.
    -   **Fragments**: Modularização da UI para as telas de "Carros" e "Favoritos".
    -   **RecyclerView**: Exibição eficiente de listas longas de dados.
    -   **Material Design**: Componentes como `FloatingActionButton`, `TabLayout` e `CardView` para uma experiência de usuário moderna.
-   **Rede (Networking):**
    -   **Retrofit**: Cliente HTTP para consumir a API REST de forma segura e estruturada.
    -   **Gson**: Biblioteca para converter objetos Kotlin em JSON e vice-versa.
-   **Carregamento de Imagens:**
    -   **Glide**: Gerenciamento eficiente de download, cache e exibição de imagens a partir de URLs.
-   **Persistência de Dados:**
    -   **SQLite**: Banco de dados local para salvar os carros favoritos.
    -   **SQLiteOpenHelper**: Classe nativa para gerenciar a criação e versionamento do banco de dados (`onCreate`, `onUpgrade`).

## 🚀 Como Executar
``` bash
# 1. Clone o repositório
git clone https://github.com/SEU_USUARIO/CombustionCarApp.git

# 2. Abra o projeto no Android Studio

# 3. Compile e execute o aplicativo em um emulador ou dispositivo físico

```


## 🎓 Aprendizados

Através deste projeto, foram aplicados e aprofundados os seguintes conhecimentos:

-   Consumo de uma API REST com Retrofit, incluindo o tratamento de sucesso e falha.
-   Implementação de um banco de dados local com SQLite para persistência de dados.
-   Gerenciamento de ciclo de vida e versionamento do banco de dados com `SQLiteOpenHelper`.
-   Aplicação do padrão de arquitetura Repository para separar responsabilidades.
-   Uso avançado do `RecyclerView` com um `Adapter` customizado para exibir dados complexos.
-   Gerenciamento de estado da UI (loading, erro, sucesso).
-   Navegação entre `Activities` e gerenciamento de `Fragments` com `TabLayout`.
-   Boas práticas na depuração de aplicativos com o **Logcat**.

## 🙏 Agradecimentos

Agradeço à **Cognizant** e à **DIO** pela oportunidade de aprendizado e pelo excelente conteúdo fornecido durante o bootcamp **Mobile Developer**.
