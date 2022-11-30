import controllers.FormularioController;

public class main {
    public static void main(String[] args) {
        FormularioController formularioController = new FormularioController();

        String json = "{\n" +
                "    \"nome\":\"testeJson\",\n" +
                "    \"email\":\"testeEmail\",\n" +
                "    \"cpf\":\"70778358127\",\n" +
                "    \"escolaridade\":\"adsfasadsf\",\n" +
                "\n" +
                "    \"endereco\":{\n" +
                "        \"cidade\":\"cidadeTeste\",\n" +
                "        \"bairro\":\"adfasd\",\n" +
                "        \"rua\":\"adsasdfa\",\n" +
                "        \"quadra\":61,\n" +
                "        \"casa\":78,\n" +
                "        \"cep\":\"74914180\",\n" +
                "        \"lote\":10,\n" +
                "        \"numero\":10,\n" +
                "        \"uf\":\"GO\"\n" +
                "    },\n" +
                "\n" +
                "    \"contatos\":[\n" +
                "        {\n" +
                "            \"nome\":\"testeJson Contato1\",\n" +
                "            \"telefone\":\"\",\n" +
                "            \"email\":\"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"nome\":\"testeJson Contato2\",\n" +
                "            \"telefone\":\"\",\n" +
                "            \"email\":\"\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}
