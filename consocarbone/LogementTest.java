package consocarbone;

public class LogementTest{
    private Logement LogUnderTest;

    @BeforeEach 
    public void initAlim(){
        LogUnderTest = new Logement();
    }

    @AfterEach
    public void undefAlim(){
        LogUnderTest = null; 
    }

    //test du calcul de l'impact :
    @Test
    public void test1(){
        LogUnderTest.setSuperficie(30);
        LogUnderTest.setCe(CE.B);
        assertEquals(30*0.01,LogUnderTest.getImpact());
    }

    //test de la maj de l'impact :
    @Test
    public void test2(){
        LogUnderTest.setSuperficie(30);
        LogUnderTest.setCe(CE.B);
        LogUnderTest.setSuperficie(45);
        assertEquals(45*0.01,LogUnderTest.getImpact());
    }

}