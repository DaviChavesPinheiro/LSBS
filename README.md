## Equipe
- Davi Chaves Pinheiro - 500187
- Paulo Ricardo Fernandes Rodrigues - 496094
- Kaio Queiroga Cordeiro Marques - 501738
- Jose Lucas Silva Marques - 497542
- Jeová Saldanha da Silva - 495111

***

## Compilação
Use o comando **make** para compilar e executar.

Caso não tenha o make, utilize:
- Compilar: ```javac -d bin/ src/Main.java```
- Executar: ```java -classpath bin/ src/Main```

***

## Passo-a-Passo
![Aplicação](/App.png)
1. Clique no botão do meio para selecionar uma imagem fonte que será usada para inserir ou extrair arquivos. **Apenas imagens PNG são suportadas**.
2. Para inserir arquivos dentro da imagem fonte, clique no botão **Add File** e selecione os arquivos. O número no canto superior esquerdo da aplicação indica o quanto de espaço a imagem fonte ainda suporta. Quando terminar de adicionar os arquivos, clique no botão de download no canto superior direito para salvar a imagem com os arquivos escondidos.
3. Para extrair arquivos da imagem fonte, clique no botão **Extract**. Para salvar os arquivos extraidos, clique no botão **Save All**.

***

## Descrição do Projeto
O **LSBS** é uma aplicação de esteganografia que utiliza o bit menos significativo.

O projeto está organizado em 5 pastas dentro do **src/**:

<details open>
<summary>Model</summary>
<p>Arquivos que contém os dados que serão usados pelos controllers e views.</p>
</details>

<details open>
<summary>View</summary>
<p>Arquivos que contém os códigos responsáveis por renderizar a parte gráfica da aplição.</p>
</details>

<details open>
<summary>Controller</summary>
<p>Arquivos que cuidam da lógica de negócio da aplicação. Também são responsáveis por agir como uma interface entre a view e o model</p>
</details>

<details open>
<summary>Utils</summary>
<p>Classes variadas</p>
</details>

<details open>
<summary>Images</summary>
<p>Imagens útilizadas na aplicação, como os ícones.</p>
</details>

O projeto foi feito usando o Observer como Design-Pattern. Isto é, ao clicar em algum botão, por exemplo, uma função de algum controller será executada e vai alterar algum model. Então, todos as views que estavam "escutando" aquele módel serão atualizadas.




