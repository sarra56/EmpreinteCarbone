    public Utilisateur(Alimentation alimentation, BienConso bienConso, LinkedList<Logement> colLogement, Transport transport,ServicesPublics services){
        this.id = i++;
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.transport = transport;
        this.services = services;
        this.colLogement = colLogement;
        this.empreinte = this.calculerEmpreinte() + this.empreinteColLogement();
    }

    public Utilisateur(Alimentation alimentation, BienConso bienConso, Logement logement, Collection<Transport> colTransport,ServicesPublics services){
        this.id = i++;
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.logement = logement;
        this.services = services;
        this.colTransport = colTransport;
        this.empreinte = this.calculerEmpreinte() + this.empreinteColTransport() ;
    }

    public Utilisateur(Alimentation alimentation, BienConso bienConso, LinkedList<Logement> colLogement, LinkedList<Transport> colTransport,ServicesPublics services){
        this.id = i++;
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.logement = logement;
        this.services = services;
        this.colLogement = colLogement;
        this.colTransport = colTransport;
        this.empreinte = this.calculerEmpreinte() + this.empreinteColLogement() + this.empreinteColTransport();
    }