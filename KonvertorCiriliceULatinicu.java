package cirilicaULatinicu;

public class KonvertorCiriliceULatinicu {

    /**
     * @String cyrillicSerbian sadrzi sva slova srpske azbuke, velika i mala, sa praznim mestom na pocetku,
     * znakovima : tacka, zarez, znak uzvika, znak pitanja, dve tacke, tacka zarez, kako bi
     * mogao da prevodi i recenice. Slova LJ NJ I DZ nisu dodata jer se sastoje od 2 karaktera u latinici.
     */
    private String cyrillicSerbian = " .,!?:;АБВГДЂЕЖЗИЈКЛМНОПРСТЋУФХЦЧШабвгдђежзијклмнопрстћуфхцчш";//bez LJ NJ I DZ jer imaju 2 slova

    /**
     * @String latinSerbian sadrzi sva slova srpske abecede ali poredjana su po redosledu srpske azbuke
     * kako bi se ova dva Stringa mogli uporedjivati lakse. Svaki znak u @String cyrillicSerbian se nalazi na tacno istom
     * mestu kao i u @String latinSerbian.
     */
    private String latinSerbian = " .,!?:;ABVGDĐEŽZIJKLMNOPRSTĆUFHCČŠabvgdđežzijklmnoprstćufhcčš";

    /**
     * Ova metoda prevodi sa cirilice na latinicu. Koristi StringBuilder umesto String.
     * Prvo uneta rec prolazi kroz for petlju
     * gde se svako slovo reci poredi sa @String cyrillicSerbian (unutrasnja for petlja). Kada pronadje podudaranje
     * vrsimo dodavanje na @StringBuilder nova pozivanjem funkcije append, koja kao parametar prima char znak latinice
     * koji se nalazi na mestu na kojem je pronadjen taj znak u cirilici( variabla j u unutrasnjoj for petlji, posto
     * i cirilica i latinica imaju isti broj karaktera, na istim mestima u String-u).
     * SWITCH naredba uzima za poredjenje slovo iz reci koja se daje kao parametar metode. Ako je neko slovo te reci
     * jednako LJ , NJ ili Dz veliko i malo , onda prevodi u latinicno slovo koje ima dva karaktera exm Dz
     * @param wordInCyrillic cirlicna rec koju treba prevesti
     * @return
     */
    public String cyrillicToLatin(String wordInCyrillic) {
        StringBuilder nova = new StringBuilder();
        for (int i = 0; i < wordInCyrillic.length(); i++) {
            for(int j = 0; j < cyrillicSerbian.length(); j++){
                if(wordInCyrillic.charAt(i)== cyrillicSerbian.charAt(j)){
                    nova.append(latinSerbian.charAt(j));
                }
            }
            switch (wordInCyrillic.charAt(i)) {
                case 'Љ' -> nova.append("Lj");
                case 'љ' -> nova.append("lj");
                case 'Њ' -> nova.append("Nj");
                case 'њ' -> nova.append("nj");
                case 'Џ' -> nova.append("Dž");
                case 'џ' -> nova.append("dž");
            }
        }
        return nova.toString();
    }

    /**
     * Radi isto sto i pretodna metoda, samo su @String cyrillicSerbian i latinSerbian zaminili mesta u petlji.
     * Jos jedna razlika je u okviru switch naredbe. Ne mozemo porediti samo jedan znak char kao u prethodnoj metodi
     * vec cemo porediti String od dva slova koliko i Dj, Nj i Lj imaju karaktera.
     * To radimo tako sto nalazimo  wordInLatin.charAt(i) + "" + wordInLatin.charAt(i + 1). Prazan String je dodat da bi
     * desni izraz bio string bez njega bi bio int tipa.
     * Poredjenje se vrsi sve do pretposlednjeg slova u reci @String wordInLatin, inace ce baciti gresku posto ce
     * i biti za jedan duze od reci.
     * @param wordInLatin rec na latinici koju treba prevesti na cirilicu
     * @return rec na cirilici.
     */
    public String latinToCyrillic(String wordInLatin){
        StringBuilder word = new StringBuilder();
        for(int i = 0; i < wordInLatin.length();i++){
            for(int j = 0; j < latinSerbian.length();j++){
                if(wordInLatin.charAt(i)==latinSerbian.charAt(j)){
                    word.append(cyrillicSerbian.charAt(j));
                }
            }
            if(i < wordInLatin.length()-1) {
                String parOfLetters = wordInLatin.charAt(i) + "" + wordInLatin.charAt(i + 1);
                switch (parOfLetters) {
                    case "Lj" -> word.append("Љ");
                    case "lj" -> word.append("љ");
                    case "Nj" -> word.append("Њ");
                    case "nj" -> word.append("њ");
                    case "Dž" -> word.append("Џ");
                    case "dž" -> word.append("џ");
                }
            }
        }
        return word.toString();
    }
}

class Test {
    public static void main(String[] args) {
        KonvertorCiriliceULatinicu k = new KonvertorCiriliceULatinicu();
        System.out.println("Rec kika cirilicno na latinici je: " + k.cyrillicToLatin("њањава"));
        System.out.println("s latinice na crilicu: "  + k.latinToCyrillic("Sta zelis od mene, ti?"));
    }
}