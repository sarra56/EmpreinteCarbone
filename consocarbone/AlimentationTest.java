package consocarbone;

public class AlimentationTest{
    private Alimentation AlimUnderTest;

    @BeforeEach 
    public void initAlim(){
        AlimUnderTest = new Alimentation();
    }

    @AfterEach
    public void undefAlim(){
        AlimUnderTest = null; 
    }

    //on teste que le taux ne pp prendre de valeur <0 ou >1
    @Test
    public void test1(){
        AlimUnderTest.setTxBoeuf(-2);
        AlimUnderTest.setTxVege(9);
        assertEquals(0,AlimUnderTest.getTxBoeuf());
        assertEquals(0,AlimUnderTest.getTxVege());
    }

    //tester si tx1 + tx2 > 1 données non cohérentes 
    @Test
    public void test2(){
        AlimUnderTest.setTxBoeuf(0.6);
        AlimUnderTest.setTxVege(0.5);
        assertEquals(0,AlimUnderTest.getTxVege());
    }

    //test du calcul de l'impact
    @Test
    public void test3(){
        AlimUnderTest.setTxBoeuf(0.6);
        AlimUnderTest.setTxVege(0.4);
        assertEquals(8*0.6 + 1.6*(1-0.4-0.6) + 0.9*0.4,AlimUnderTest.getImpact());
    }

    //test de la maj de l'impact 
    @Test
    public void test3(){
        AlimUnderTest.setTxBoeuf(0.6);
        AlimUnderTest.setTxVege(0.4);
        AlimUnderTest.setTxBoeuf(0.3);
        assertEquals(8*0.3 + 1.6*(1-0.4-0.3) + 0.9*0.4,AlimUnderTest.getImpact());
    }
}