# -*- coding: utf-8 -*- 
from tkinter import *
# Parce que la communauté le load comme ca.
# On fait pas de class au meme noms que kkc ds tkinter
import math
import random
from helper import Helper
from random import randint, randrange
import os.path #New import pour les files


class CoursDeau():
    def __init__(self, centreX, centreY):
        self.largeur = 16
        self.longueur = 16
        self.centreX = centreX
        self.centreY = centreY
        self.imageActuelle = None


class Montagne():
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.rayon = random.randint(50, 100)
        self.couleur = "forest green"

                                                              
class Ravin():
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.isRempli = False
        self.couleur = "dark olive green"
        self.largeur = random.randint(10, 150)
        if self.largeur > 50:
            self.hauteur = random.randint(10, 30)
        else:
            self.hauteur = random.randint(70, 150)  # #

 
class Animal():
    def __init__(self, parent, x, y, id,papa,maman):
        self.parent = parent
        self.x = x
        self.y = y
        self.cibleabouffer = None
        self.ciblePredateur = None
        self.cibleAmour = None
        self.cibleTempo = None
        self.longueurPasHiking = 0.1#self.longueurPas - ((random.randint(65, 100)) / 400)
        self.age = 0  # ajout variable age pour savoir la longevite de l'animal
        self.auRepo = 0
        self.champVision = 30
        self.id = id
        self.tourInactif=0
        self.sexe=randint(0,1) #0=male,1=female
        self.hadBaby=0
        self.hadBabyTrigger=150
        self.IDPapa=papa
        self.IDmaman=maman
        self.Mating=0
        self.angle = 0
        
    def trouveAmour(self, memeEspece):
        cibletemp = [None, 0]
        if self.hadBaby>self.hadBabyTrigger:
            for i in memeEspece:
                if i != self:
                    dist = Helper.calcDistance(self.x, self.y, i.x, i.y)
                    if cibletemp[0]:
                        if (dist < cibletemp[1] 
                            and dist <= self.champVision 
                            and self.sexe!=i.sexe 
                            and i.hadBaby>self.hadBabyTrigger
                            and self.IDmaman != i.id and self.IDPapa != i.id):
                            cibletemp = [i, dist]        
                    elif (dist <= self.champVision 
                          and self.sexe!=i.sexe 
                          and i.hadBaby>self.hadBabyTrigger
                          and self.IDmaman != i.id and self.IDPapa != i.id):
                        cibletemp = [i, dist]
                        
                self.cibleAmour = cibletemp[0]
                if self.cibleAmour:
                    self.Mating=1
                    self.cibleTempo =None
 
    def chasse(self, proies, proies2):
            cibletemp = [None, 0]
            cible2Temp=[None,0]
            for i in proies:
                dist = Helper.calcDistance(self.x, self.y, i.x, i.y)
                if cibletemp[0]:
                    if dist < cibletemp[1] and dist <= self.champVision:
                        cibletemp = [i, dist]
                elif dist <= self.champVision:
                    cibletemp = [i, dist]
            self.cibleabouffer = cibletemp[0]
           
            if proies2:
                for i in proies2:
                    dist = Helper.calcDistance(self.x, self.y, i.x, i.y)
                    if cible2Temp[0]:
                        if dist < cible2Temp[1] and dist <= self.champVision:
                            cible2Temp = [i, dist]
                    elif dist <= self.champVision:
                        cible2Temp = [i, dist]
                if cibletemp[0]:
                    if cible2Temp[1]<cibletemp[1]:
                        self.cibleabouffer = cible2Temp[0]
                else:
                    self.cibleabouffer=cible2Temp[0]
            if self.cibleabouffer:
                self.cibleTempo =None
      
    def fuirPredateur(self, predateurs):
        cibletemp = [None, 0]
        for i in predateurs:
            dist = Helper.calcDistance(self.x, self.y, i.x, i.y)
            if cibletemp[0]:
                if dist < cibletemp[1] and dist <= self.champVision:
                    cibletemp = [i, dist]
            elif dist <= self.champVision:
                cibletemp = [i, dist]
        self.ciblePredateur = cibletemp[0]
        if self.ciblePredateur:
            self.cibleTempo =None
   
    def trouverCible(self, proies, proies2, predateurs, memeEspece):
        if predateurs:
            self.fuirPredateur(predateurs)
        else:
            self.ciblePredateur = None
        if self.ciblePredateur == None:
            if self.energie >= (60 / 100 * self.maxEnergie) and self.Mating==0:
                self.trouveAmour(memeEspece)
            else:
                self.chasse(proies,proies2)
        if self.cibleTempo:
            if (self.x-self.cibleTempo.x)<2 and (self.y-self.cibleTempo.y)<2:
                self.cibleTempo=None
        if self.cibleAmour == None and self.cibleabouffer == None and self.cibleTempo==None:
            self.cibleTempo = Lapin(self.parent, randint(0, self.parent.largeur), randint(0, self.parent.hauteur), 999,999,999)
   
    def bouge(self,action):          
        if self.ciblePredateur:
            self.deplacer(self.ciblePredateur.x, self.ciblePredateur.y,0)
            self.cibleTempo=None
        elif self.cibleAmour:
            self.deplacer(self.cibleAmour.x, self.cibleAmour.y)
        elif self.cibleabouffer:
            self.deplacer(self.cibleabouffer.x, self.cibleabouffer.y, action)
        else:
            self.deplacer(self.cibleTempo.x, self.cibleTempo.y)
   
    def deplacer(self, cibleX, cibleY, action=1):
        self.angle = Helper.calcAngle(self.x, self.y,
                                 cibleX,
                                 cibleY)
       
        if action == 1:
            newPosition = Helper.getAngledPoint(self.angle,
                                                self.longueurPas,
                                                self.x, self.y)
        elif action == 0:
            self.angle2 = math.degrees(self.angle) + 180
            self.angle = math.radians(self.angle2)
            newPosition = Helper.getAngledPoint(self.angle,
                                                self.longueurPas,
                                                self.x, self.y)
        else:
            newPosition = Helper.getAngledPoint(self.angle,
                                                self.longueurPas * self.sprint,
                                                self.x, self.y)
            self.energie -= self.perteEnergieSprint
            self.fatigue -= self.perteFatigueSprint
           
        self.x = newPosition[0]
        self.y = newPosition[1]
        if self.x > self.parent.largeur:
            self.x = self.parent.largeur
        elif self.x < 0:
            self.x = 0
        if self.y > self.parent.hauteur:
            self.y = self.parent.hauteur
        elif self.y < 0:
            self.y = 0
    
    def manger(self, proies, proies2):      
        self.energie += 0.4 * self.cibleabouffer.energie  # Gossage avec energie
        if self.energie > self.maxEnergie:  # Be sure energie doesnt > maxenergie
            self.energie = self.maxEnergie
        if self.cibleabouffer in proies:
            proies.remove(self.cibleabouffer)
        if proies2:
            if self.cibleabouffer in proies2:
                proies2.remove(self.cibleabouffer)
        self.cibleabouffer=None
        
    def reproduction(self,memeEspece):
        self.tourInactif+=1
        self.energie-=6
        if self.tourInactif==20 and self.sexe==1:
            self.tourInactif=0
            self.hadBaby=0
            self.Mating=0
            if self.typeAnimal=="Loup":
                memeEspece.append(Loup(self.parent,self.x+1,self.y+1,self.parent.IDcount,self.cibleAmour.id,self.id))
                self.parent.IDcount+=1
                print("bebe Loup"+str(self.parent.IDcount-1))
            elif self.typeAnimal=="Lapin":
                memeEspece.append(Lapin(self.parent,self.x+1,self.y+1,self.parent.IDcount,self.cibleAmour.id,self.id))
                self.parent.IDcount+=1
                memeEspece.append(Lapin(self.parent,self.x-1,self.y-1,self.parent.IDcount,self.cibleAmour.id,self.id))
                self.parent.IDcount+=1
                memeEspece.append(Lapin(self.parent,self.x+1.5,self.y-1.5,self.parent.IDcount,self.cibleAmour.id,self.id))
                self.parent.IDcount+=1
            elif self.typeAnimal=="Panda roux":
                memeEspece.append(Pandaroux(self.parent,self.x+1,self.y+1,self.parent.IDcount,self.cibleAmour.id,self.id))
                self.parent.IDcount+=1
            elif self.typeAnimal=="Raton":
                memeEspece.append(Raton(self.parent,self.x+1,self.y+1,self.parent.IDcount,self.cibleAmour.id,self.id))
                self.parent.IDcount+=1
            elif self.typeAnimal=="Ours":
                memeEspece.append(Ours(self.parent,self.x+1,self.y+1,self.parent.IDcount,self.cibleAmour.id,self.id))
                self.parent.IDcount+=1
            elif self.typeAnimal=="Caribou":
                memeEspece.append(Caribou(self.parent,self.x+1,self.y+1,self.parent.IDcount,self.cibleAmour.id,self.id))
                self.parent.IDcount+=1
            self.cibleAmour=None
        elif self.tourInactif==20 and self.sexe==0:
            self.tourInactif=0
            self.hadBaby=0
            self.Mating=0
            self.cibleAmour=None
    
    def evolue(self, proies,proies2, predateurs, memeEspece, action):
        self.energie -= 1
        self.fatigue -= 1
        self.hadBaby +=1
       
        if self.auRepo == 0:
            self.trouverCible(proies, proies2, predateurs, memeEspece)
            self.bouge(action)
           
            if self.cibleAmour:
                dist = Helper.calcDistance(self.x, self.y, self.cibleAmour.x, self.cibleAmour.y)
                if dist < 2:
                    self.reproduction(memeEspece)
               
            if self.cibleabouffer:
                dist = Helper.calcDistance(self.x, self.y, self.cibleabouffer.x, self.cibleabouffer.y)
                if dist < 2:
                    self.manger(proies,proies2)         
        else:
            self.fatigue += 3
       
        if self.fatigue < 10:
            self.auRepo = 1
        elif self.fatigue >= self.fatigueMax:
            self.auRepo = 0
           
        self.Hiking()
        self.changementImage()
        
    def changementImage(self):
        angle = math.degrees(self.angle)%360 
        
        if 45 <= angle < 135:
            self.imageActuelle = self.imageDevant
        elif  135 <= angle < 225.0:
            self.imageActuelle = self.imageGauche
        elif 225.0 <= angle < 315.0:   
            self.imageActuelle = self.imageDerriere
        else:
            self.imageActuelle = self.imageDroite
                    
    def Hiking(self):
        for j in self.parent.Montagnes:
                    distance = Helper.calcDistance(self.x, self.y, j.x, j.y)
                    if distance < j.rayon:
                        if self.longueurPas > self.longueurPasHiking:
                            temp = self.longueurPas
                            self.longueurPas = self.longueurPasHiking
                            self.longueurPasHiking = temp
                    else:
                        if self.longueurPas < self.longueurPasHiking:
                            temp = self.longueurPas
                            self.longueurPas = self.longueurPasHiking
                            self.longueurPasHiking = temp                   
 
class Loup(Animal):
    def __init__(self, parent, x, y, id,papa,maman):
        self.parent=parent
        Animal.__init__(self, self.parent, x, y, id,papa,maman)
        self.longueurPas = (random.randint(85, 100)) / 200
        self.energie = random.randint(1250, 1750)  # Energie de depart kinda random
        self.maxEnergie = random.randint(1750, 2500)  # max energie est ca
        self.fatigueMax = random.randint(200, 300)
        self.fatigue = self.fatigueMax
        self.couleur = "grey"
        self.champVision = 50
        self.sprint = 3
        self.perteEnergieSprint = 2
        self.perteFatigueSprint = 2
        self.souffle = 85
        self.typeAnimal="Loup"
       
        if self.sexe == 0:
            self.imageDroite = parent.parent.parent.vue.loupmaledroit
            self.imageGauche = parent.parent.parent.vue.loupmalegauche
            self.imageDerriere = parent.parent.parent.vue.loupmalederriere
            self.imageDevant = parent.parent.parent.vue.loupmaledevant
        else:
            self.imageDroite = parent.parent.parent.vue.loupfemelledroit
            self.imageGauche = parent.parent.parent.vue.loupfemellegauche
            self.imageDerriere = parent.parent.parent.vue.loupfemellederriere
            self.imageDevant = parent.parent.parent.vue.loupfemelledevant
        
        self.imageActuelle = self.imageDevant
 
     
class Lapin(Animal):
    def __init__(self, parent, x, y, id,papa,maman):
        self.parent=parent
        Animal.__init__(self, self.parent, x, y, id,papa,maman)
        self.longueurPas = ((random.randint(65, 85)) / 200)
        self.energie = random.randint(1000, 1250)  # energie depart random
        self.maxEnergie = random.randint(1250, 1750)  # max energie est 1.5x a 2x energie de depart
        self.fatigueMax = random.randint(400, 500)
        self.fatigue = self.fatigueMax
        self.champVision = 35
        self.couleur = "white smoke"
        self.souffle = 65
        self.typeAnimal="Lapin"
        
        if self.sexe == 0:
            self.imageDroite = parent.parent.parent.vue.lapindroit
            self.imageGauche = parent.parent.parent.vue.lapingauche
            self.imageDerriere = parent.parent.parent.vue.lapinderriere
            self.imageDevant = parent.parent.parent.vue.lapindevant
        else:
            self.imageDroite = parent.parent.parent.vue.lapinedroit
            self.imageGauche = parent.parent.parent.vue.lapinegauche
            self.imageDerriere = parent.parent.parent.vue.lapinederriere
            self.imageDevant = parent.parent.parent.vue.lapinedevant
        
        self.imageActuelle = self.imageDevant
       
       
class Raton(Animal):
    def __init__(self, parent, x, y, id, papa, maman):
        self.parent=parent
        Animal.__init__(self, self.parent, x, y, id, papa, maman)
        self.longueurPas = ((random.randint(55, 70)) / 200)
        self.energie = random.randint(1150, 1300)  # energie depart random
        self.maxEnergie = random.randint(1200, 1400)  # max energie est 2x a 2.5x energie de depart
        self.fatigueMax = random.randint(350, 450)
        self.fatigue = self.fatigueMax
        self.champVision = 40
        self.couleur = "burlywood4"
        self.souffle = 85
        self.typeAnimal="Raton"
        
        self.imageDroite = parent.parent.parent.vue.ratondroit 
        self.imageGauche = parent.parent.parent.vue.ratongauche
        self.imageDerriere = parent.parent.parent.vue.ratonderriere
        self.imageDevant = parent.parent.parent.vue.ratondevant
        
        self.imageActuelle = self.imageDevant
        
class Pandaroux(Animal):
    def __init__(self, parent, x, y, id, papa, maman):
        self.parent=parent
        Animal.__init__(self, self.parent, x, y, id,papa, maman)
        self.longueurPas = ((random.randint(65, 85)) / 200)
        self.energie = random.randint(1200, 1300)  # energie depart random
        self.maxEnergie = random.randint(1300, 1650)  # max energie est 2x a 2.5x energie de depart
        self.fatigueMax = random.randint(400, 500)
        self.fatigue = self.fatigueMax
        self.champVision = 30
        self.couleur = "brown4"
        self.souffle = 95
        self.typeAnimal="Panda roux"
 
        self.imageDroite = parent.parent.parent.vue.pandadroit
        self.imageGauche = parent.parent.parent.vue.pandagauche
        self.imageDerriere = parent.parent.parent.vue.pandaderriere
        self.imageDevant = parent.parent.parent.vue.pandadevant
        
        self.imageActuelle = self.imageDevant

class Ours(Animal):
    def __init__(self, parent, x, y, id,papa,maman):
        self.parent=parent
        Animal.__init__(self, self.parent, x, y, id,papa,maman)
        self.longueurPas = (random.randint(85, 120)) / 200
        self.energie = random.randint(1400, 1900)  # Energie de depart kinda random
        self.maxEnergie = random.randint(1750, 2250)  # max energie est ca
        self.fatigueMax = random.randint(350, 450)
        self.fatigue = self.fatigueMax
        self.couleur = "Brown"
        self.champVision = 65
        self.souffle = 120
        self.perteEnergieSprint = 2
        self.perteFatigueSprint = 2
        self.typeAnimal="Ours"
        self.sprint = 3
       
        self.imageDroite = parent.parent.parent.vue.oursdroit
        self.imageGauche = parent.parent.parent.vue.oursgauche
        self.imageDerriere = parent.parent.parent.vue.oursderriere
        self.imageDevant = parent.parent.parent.vue.oursdevant
 
        self.imageActuelle = self.imageDevant
 
class Caribou(Animal):
    def __init__(self, parent, x, y, id, papa, maman):
        self.parent=parent
        Animal.__init__(self, self.parent, x, y, id, papa, maman)
        self.longueurPas = ((random.randint(55, 70)) / 200)
        self.energie = random.randint(1300, 1500)  # energie depart random
        self.maxEnergie = random.randint(1300, 1700)  # max energie est 2x a 2.5x energie de depart
        self.fatigueMax = random.randint(450, 600)
        self.fatigue = self.fatigueMax
        self.champVision = 60
        self.souffle = 120
        self.typeAnimal="Caribou"
        self.perteEnergieSprint = 2
        self.perteFatigueSprint = 2
        self.sprint = 3
       
        self.imageDroite = parent.parent.parent.vue.cariboudroit
        self.imageGauche = parent.parent.parent.vue.caribougauche
        self.imageDerriere = parent.parent.parent.vue.caribouderriere
        self.imageDevant = parent.parent.parent.vue.cariboudevant
       
        self.imageActuelle = self.imageDevant 
 
class Laitue():
    def __init__(self, parent, x, y, id):
        self.parent = parent
        self.x = x
        self.y = y
        self.energie = random.randint(500, 1000)  # Energie depart kinda random
        self.age = 0  # ajout variable age pour savoir la duree de la laitue
        self.enTrainGrossir = True  # Laitue grossit, then degrossit cause why not
        self.id = id
        self.typeAnimal="laitue"
        self.stade = 0
        self.souffle = None
   
   
    def evolue(self, lapins):
        if self.enTrainGrossir:  # Si elle grossit energie++
            self.energie += 1
        else:
            self.energie -= 1  # Sinon decroit
        self.age += 1
        if self.age > 100:  # Combien de ticks pour que la laitue decroit
            self.enTrainGrossir = False
            self.stade = 1 # Jouer avec les couleurs
        if self.energie < 400:  # Jouer avec les couleurs
            self.stade = 2
       
    #    for i in lapins:
    #       dist=Helper.calcDistance(self.x,self.y,i.x,i.y)
    #        if cibletemps[0]:
    #            if dist
    
    
class Baies(Laitue):
    def __init__(self, parent, x, y, id):
        self.typeAnimal="Baies d'hiver"
        Laitue.__init__(self, parent, x, y, id)
 
 
class Environnement():
    def __init__(self, parent, largeur, hauteur, loup, lapin, laitue, raton, pandaroux, ours, caribou):
        self.parent = parent
        self.counterTime = 0
        self.largeur = largeur
        self.hauteur = hauteur
        self.IDcount = 0
        self.Montagnes = []                                
        self.Ravins = []                                    
        self.creerRelief(largeur, hauteur)      
        self.loups = []
        self.lapins = []
        self.laitues = []
        self.baies = []
        self.ratons = []
        self.pandarouxs = []
        self.lesOurs = []
        self.caribous = []
        self.coursDeau = []  # Liste de cours d'eau
        self.ours = []
        self.peupler(largeur, hauteur, loup, lapin, laitue, raton, pandaroux, ours, caribou)
        self.angleTemp = randint(150, 220)
        self.eau1 = True
        self.eau2 = True
        self.lapinNaissant = 2
        self.loupNaissant = 1
        self.laitueNaissant = 4
        self.baiesNaissant = 3
        self.ratonNaissant = 3
        self.pandarouxNaissant = 1
        self.oursNaissant = 1
        self.caribouNaissant = 1
        self.Jour = True
        self.couleur = "darkgreen"
        self.directionEau = randint(0, 360)  # angle 0-360 pour direction cours d'eau
        self.nombreUpdateEau = 1
        self.saison = "ete"
        self.creerEau()
        self.ajouterEau()
        
    def creerRelief(self, largeur, hauteur):
        nbMontagnes = random.randint(1, 3)                
        nbRavins = random.randint(1, 2)
       
        for i in range(nbMontagnes):
            positionok = False
            while(positionok == False):
                positionok = True
                x = random.randrange(largeur)
                y = random.randrange(hauteur)  
                for j in self.Montagnes:
                    distance = Helper.calcDistance(x, y, j.x, j.y)
                    if distance < j.rayon:
                        positionok = False
            self.Montagnes.append(Montagne(x, y))
           
        for i in range(nbRavins):
            positionok = False
            while(positionok == False):
                positionok = True
                x = random.randrange(largeur)
                y = random.randrange(hauteur)
                for j in self.Montagnes:
                    distance = Helper.calcDistance(x, y, j.x, j.y)
                    if distance < j.rayon + 20:
                        positionok = False
                for j in self.Ravins:
                    distance = Helper.calcDistance(x, y, j.x, j.y)
                    if distance < j.largeur or distance < j.hauteur:
                        positionok = False
                   
            self.Ravins.append(Ravin(x, y))  
 
    def creerEau(self):
        positionok = False
        while(positionok == False):
            positionok = True
            centreX = (int(self.largeur/2) + (randint(0, int(self.largeur/3)) - 200))
            centreY = (int(self.hauteur/2) + (randint(0, int(self.hauteur/3)) - 200))
            for j in self.Montagnes:
                distance = Helper.calcDistance(centreX, centreY, j.x, j.y)
                if distance < j.rayon + 20:
                    positionok = False
            for j in self.Ravins:
                distance = Helper.calcDistance(centreX, centreY, j.x, j.y)
                if distance < j.largeur or distance < j.hauteur:
                    positionok = False
        self.coursDeau.append(CoursDeau(centreX, centreY))
        self.verifCollisionEau()
          
    def ajouterEau(self):
        centreX = self.coursDeau[0].centreX
        centreY = self.coursDeau[0].centreY
        temp = Helper.getAngledPoint(self.directionEau, 14 * self.nombreUpdateEau, centreX, centreY)
       
        if temp[0] < 0 or temp[1] < 0:
            self.eau1 = False
        if temp[0] > self.largeur or temp[1] > self.hauteur:
            self.eau2 = False
           
        if temp[1] < 0 or temp[0] < 0:
            self.eau1 = False
        if temp[1] > self.largeur or temp[0] > self.hauteur:
            self.eau2 = False
       
        self.coursDeau.append(CoursDeau(temp[0], temp[1]))
       
        temp = Helper.getAngledPoint(self.directionEau + self.angleTemp, 14 * self.nombreUpdateEau, centreX, centreY)
        self.coursDeau.append(CoursDeau(temp[0], temp[1]))
        self.nombreUpdateEau += 1
        self.verifCollisionEau()
       
    def ajouterLaitueProcheEau(self):
        for i in self.coursDeau:
            if randint(0, 3) == 2:
                self.laitues.append(Laitue(self, i.centreX + randint(-16, 16), i.centreY + randint(-16, 16), self.IDcount))
                self.IDcount += 1
        self.verifCollisionEau()
       
    def verifCollisionEau(self):                                            #Verif collision eau avec les autres trucs PLUS GERE LE SOUFFLE DES ANIMAUX. Cause Water gere animals. Legit.
        for i in self.coursDeau:
            for j in self.Ravins:
                if (Helper.calcDistance(i.centreX, i.centreY, j.x, j.y) <=j.largeur):
                    j.isRempli = True
                    #j.couleur = "blue4"
                  
            for j in self.lapins:
                if (i.centreX - 10 <= j.x and i.centreX+10 >= j.x) and (i.centreY - 10 <= j.y and i.centreY+10 >= j.y):
                    j.souffle -= 2
                    if j.souffle <=0:
                        self.lapins.remove(j)
                       
                #elif not (i.centreX - 10 <= j.x and i.centreX+10 >= j.x) and (i.centreY - 10 <= j.y and i.centreY+10 >= j.y) and j.souffle < 65:
                #    j.souffle +=1
                           
            for j in self.loups:
                if (i.centreX - 10 <= j.x and i.centreX+10 >= j.x) and (i.centreY - 10 <= j.y and i.centreY+10 >= j.y):
                    j.souffle -= 2
                    if j.souffle <=0:
                        self.loups.remove(j)
                        
            for j in self.ratons:
                if (i.centreX - 10 <= j.x and i.centreX+10 >= j.x) and (i.centreY - 10 <= j.y and i.centreY+10 >= j.y):
                    j.souffle -= 2
                    if j.souffle <=0:
                        self.ratons.remove(j)
                        
            for j in self.pandarouxs:
                if (i.centreX - 10 <= j.x and i.centreX+10 >= j.x) and (i.centreY - 10 <= j.y and i.centreY+10 >= j.y):
                    j.souffle -= 2
                    if j.souffle <=0:
                        self.pandarouxs.remove(j)
             
            for j in self.lesOurs:
                if (i.centreX - 10 <= j.x and i.centreX+10 >= j.x) and (i.centreY - 10 <= j.y and i.centreY+10 >= j.y):
                    j.souffle -= 2
                    if j.souffle <=0:
                        self.lesOurs.remove(j)      
           
            for j in self.caribous:
                if (i.centreX - 10 <= j.x and i.centreX+10 >= j.x) and (i.centreY - 10 <= j.y and i.centreY+10 >= j.y):
                    j.souffle -= 2
                    if j.souffle <=0:
                        self.caribous.remove(j)       
                   
            for j in self.laitues:
                if (i.centreX - 10 <= j.x and i.centreX+10 >= j.x) and (i.centreY - 10 <= j.y and i.centreY+10 >= j.y):
                    self.laitues.remove(j)
                   
        for i in self.lapins:
            if i.souffle < 130:
                i.souffle += 1
                
        for i in self.loups:
            if i.souffle < 170:
                i.souffle+=1
                
        for i in self.ratons:
            if i.souffle < 130:
                i.souffle += 1
               
        for i in self.pandarouxs:
            if i.souffle < 170:
                i.souffle+=1
       
        for i in self.lesOurs:
            if i.souffle < 200:
                i.souffle+=1
       
        for i in self.caribous:
            if i.souffle < 225:
                i.souffle+=1
                                     
    def verifMontagnes(self):                                           #Verifie si ya une montagne
        for i in self.coursDeau:
            for j in self.Montagnes:
                if Helper.calcDistance(i.centreX, i.centreY, j.x, j.y) < j.circonf:
                    print("Montagnes???")
                    self.eau1 = False
                    self.eau2 = False
 
 
    def peupler(self, largeur, hauteur, loup, lapin, laitue, raton, pandaroux, ours, caribou):
        defaultDad=-1
        defaultMom=-2
        for i in range(loup):
            x = random.randrange(largeur)
            y = random.randrange(hauteur)
            self.loups.append(Loup(self, x, y, self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
        for i in range(lapin):
            x = random.randrange(largeur)
            y = random.randrange(hauteur)
            self.lapins.append(Lapin(self, x, y, self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
        for i in range(laitue):
            x = random.randrange(largeur)
            y = random.randrange(hauteur)
            self.laitues.append(Laitue(self, x, y, self.IDcount))
            self.IDcount += 1
        for i in range(raton):
            x = random.randrange(largeur)
            y = random.randrange(hauteur)
            self.ratons.append(Raton(self, x, y, self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
        for i in range(pandaroux):
            x = random.randrange(largeur)
            y = random.randrange(hauteur)
            self.pandarouxs.append(Pandaroux(self, x, y, self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
        for i in range(ours):
            x = random.randrange(largeur)
            y = random.randrange(hauteur)
            self.lesOurs.append(Ours(self, x, y, self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
        for i in range(caribou):
            x = random.randrange(largeur)
            y = random.randrange(hauteur)
            self.caribous.append(Caribou(self, x, y, self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1

    def evolue(self):
        self.counterTime += 1
       
        
        #Infinite animals
        defaultDad=-1
        defaultMom=-2
        if self.counterTime % 300==0:
            self.loups.append(Loup(self, random.randrange(self.largeur), 0, self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
            self.lapins.append(Lapin(self, 0, random.randrange(self.hauteur), self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
            self.lapins.append(Lapin(self, self.largeur, random.randrange(self.hauteur), self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
            self.pandarouxs.append(Pandaroux(self, 0, random.randrange(self.hauteur), self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
            self.pandarouxs.append(Pandaroux(self, self.largeur, random.randrange(self.hauteur), self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
            self.ratons.append(Raton(self, 0, random.randrange(self.hauteur), self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
            self.ratons.append(Raton(self, self.largeur, random.randrange(self.hauteur), self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
            self.caribous.append(Caribou(self, random.randrange(self.largeur),self.hauteur, self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
        if self.counterTime % 600==0:
            self.lesOurs.append(Ours(self, random.randrange(self.largeur),self.hauteur, self.IDcount,defaultDad,defaultMom))
            self.IDcount += 1
       
        for i in self.laitues:  # Faire le evolue des especes
            i.evolue(self.lapins+self.ratons+self.caribous+self.ours+self.pandarouxs)
        for i in self.baies:  # Faire le evolue des especes
            i.evolue(self.lapins+self.ratons+self.caribous+self.ours+self.pandarouxs)
        for i in self.lapins:
            i.evolue(self.laitues+self.baies, None, self.loups, self.lapins, 1)
        for i in self.loups:
            i.evolue(self.lapins,self.ratons, self.lesOurs, self.loups, 3)
        for i in self.ratons:
            i.evolue(self.laitues+self.baies,None, self.loups+self.ours, self.ratons, 1)
        for i in self.pandarouxs:
            i.evolue(self.laitues+self.baies,None, self.loups+self.ours, self.pandarouxs, 1)
        for i in self.lesOurs:
            i.evolue(self.lapins+self.ours, self.ratons, None, self.lesOurs, 3)
        for i in self.caribous:
            i.evolue(self.laitues,None, self.loups, self.caribous, 3)
       
        self.verifCollisionEau()
        self.gestionEnergie()  # Call gestion energie de toutes les especes
       
        if self.counterTime % (60 * 5) == 0:  # Trigger changement jour/nuit a tous les 60 * (x secondes)
            self.gestionJourNuit()
            journ = True
        else:
            journ = False
            
        if self.counterTime % (60 * 10) == 0:
            self.gestionSaisons()
            saiso = True
        else:
            saiso = False 

        if self.saison=="printemps" or self.saison=="ete":
            if randint(0, 100) == 25:
                for i in range(self.laitueNaissant):
                    x = random.randrange(self.largeur)
                    y = random.randrange(self.hauteur)
                    self.laitues.append(Laitue(self, x, y, self.IDcount))
                    self.IDcount += 1

        if self.saison=="printemps" or self.saison=="ete":
            if randint(0, 60) == 25:
                self.ajouterLaitueProcheEau()
                
        if self.saison == "automne":
            for i in self.laitues:
                i.energie -= 1
                
        if self.saison in ["automne", "hiver"]:
            if randint(0, 100) == 25:
                for i in range(self.baiesNaissant):
                    x = random.randrange(self.largeur)
                    y = random.randrange(self.hauteur)
                    self.baies.append(Baies(self, x, y, self.IDcount))
                    self.IDcount += 1
        
        if self.saison == "printemps":
            for i in self.baies:
                i.energie -= 1
       
        if self.counterTime % 5 == 1:
            if self.eau1 and self.eau2:
                self.ajouterEau()  # Ajoute cours d'eau/fait evoluer le paysage/landscape
                                
        if self.saison == "hiver":
            for i in self.coursDeau:
                i.imageActuelle = self.parent.parent.vue.eaugelee
        else:
            if self.counterTime % 10 == 1:
                for i in self.coursDeau:
                    if i.imageActuelle == self.parent.parent.vue.eau2:
                        i.imageActuelle = self.parent.parent.vue.eau3
                    elif i.imageActuelle == self.parent.parent.vue.eau3:
                        i.imageActuelle = self.parent.parent.vue.eau1
                    else:
                        i.imageActuelle = self.parent.parent.vue.eau2

        perma = False
        instan = True
        return [instan, journ, saiso, perma]
       
    def gestionEnergie(self):  # Kinda self explanatory, fonction de Drolet
        for i in self.laitues:
            if i.energie <= 0:
                self.laitues.remove(i)
        for i in self.baies:
            if i.energie <= 0:
                self.baies.remove(i)
        for i in self.lapins:
            if i.energie <= 0:
                self.lapins.remove(i)
        for i in self.loups:
            if i.energie <= 0:
                self.loups.remove(i)
        for i in self.ratons:
            if i.energie <= 0:
                self.ratons.remove(i)
        for i in self.pandarouxs:
            if i.energie <= 0:
                self.pandarouxs.remove(i)
        for i in self.lesOurs:
            if i.energie <= 0:
                self.lesOurs.remove(i)
        for i in self.caribous:
            if i.energie <= 0:
                self.caribous.remove(i)

    def gestionJourNuit(self):  # gestion nuit jour, fonction qui change de jour a nuit et nuit a jour, est triggered dans evolue
        if self.Jour:
            self.Jour = False
        else:
            self.Jour = True
               
    def gestionSaisons(self):        
        if self.saison == "ete":
            self.saison = "automne"
            self.couleur = "wheat4"

        elif self.saison == "automne":
            self.saison = "hiver"
            self.couleur = "snow"
            
        elif self.saison == "hiver":
            self.saison = "printemps"
            self.couleur = "khaki4"
            
        elif self.saison == "printemps":
            self.saison = "ete"
            self.couleur = "darkgreen"

            
class Monde():
    def __init__(self, parent, largeur, hauteur):
        self.parent = parent
        self.largeur = largeur
        self.hauteur = hauteur
        # là car on montres "toutes" les vars.
        self.environnement = None
      
    def creerenviro(self, loup, lapin, laitue, raton, pandaroux,ours,caribou):
        self.environnement = Environnement(self, self.largeur, self.hauteur,
                                         int(loup), int(lapin), int(laitue), int(raton), int(pandaroux), int(ours),
                                         int(caribou))
    def changesize(self,largeur,hauteur):
        self.largeur = largeur
        self.hauteur = hauteur 
    def evolue(self):
        if self.environnement:
            retour = self.environnement.evolue()
        return retour
 
###################VUE############################  
class Vue():
    def __init__(self, parent, largeur, hauteur):
        self.parent = parent  # un handle pour parent (le controleur)
        self.root = Tk()  # s'apelle root par convention. fenetre initiale de Tkinter
        self.largeur = largeur
        self.hauteur = hauteur
        self.root.title("Simulation")
        self.scrollX = 0
        self.scrollY = 0
        self.loadimage(self.parent)
        
        self.defsize()
        
        

        
    def defsize(self):
        self.root2 = Tk()  # s'apelle root par convention. fenetre initiale de Tkinter
        self.root.withdraw()            #Hide la windows simulation
        self.root2.title("Taille de la simulation")

        self.tailleEcran = Frame(self.root2)
        self.tailleEcran2 = Frame(self.tailleEcran)
        
        self.largeurEntry = Entry(self.tailleEcran2)
        self.largeurEntry.insert(0, "1000")
        self.hauteurEntry = Entry(self.tailleEcran2)
        self.hauteurEntry.insert(0, "1000")
        
        self.scrollX = 0
        self.scrollY = 0
        
        largeur = Label(self.tailleEcran2, text="Largeur de la Simulation")
        hauteur = Label(self.tailleEcran2, text="Hauteur de la Simulation")
        
        largeur.grid(column=0, row=0)
        hauteur.grid(column=0, row=1)
        
        self.largeurEntry.grid(column = 1, row = 0)
        self.hauteurEntry.grid(column = 1, row =1)
        
        self.btngo = Button(self.tailleEcran2, text="Go", command=self.genese)
        self.btngo.grid(column=0, row=2, columnspan=2)
        
        self.tailleEcran.pack()
        self.tailleEcran2.pack()
        
        self.root2.after(1, lambda: self.root2.focus_force())
        
        
        self.root2.protocol('WM_DELETE_WINDOW', self.doSomething)  # root is your root window // cant close the window
        
        self.root2.wm_attributes("-topmost", 1)   # Make sure window remains ontop of all others
        self.root2.focus()                        # Set focus to window
        self.root2.grab_set()




    def doSomething(self):
        print("LOL")
        
        
    def genese(self):
        self.scrollX = int(self.largeurEntry.get())
        self.scrollY = int(self.hauteurEntry.get())
        
        self.parent.Modele.largeur = self.scrollX
        self.parent.Modele.hauteur = self.scrollY
        
        self.root2.destroy()
        self.root.update()
        self.root.deiconify()
        self.creercadreenviro()
    
    def creercadreenviro(self):
        self.cadrecanevas = Frame(self.root)
        
        
        xscrollbar = Scrollbar(self.cadrecanevas, orient=HORIZONTAL)
        xscrollbar.grid(row=1, column=0, sticky=E+W)


        yscrollbar = Scrollbar(self.cadrecanevas)
        yscrollbar.grid(row=0, column=1, sticky=N+S)
        
        
        self.canevas = Canvas(self.cadrecanevas, width=self.largeur,
                            height=self.hauteur, scrollregion=(0, 0, self.scrollX, self.scrollY),
                xscrollcommand=xscrollbar.set,
                yscrollcommand=yscrollbar.set)
        
        
        
        xscrollbar.config(command=self.canevas.xview)
        yscrollbar.config(command=self.canevas.yview)
       
        self.canevas.grid(row=0, column=0, sticky=N+S+E+W)
        #Truc pour get coords du clickj (I guess)
        self.canevas.bind("<Button 1>",self.printcoords)
       
        self.cadrecanevas.pack(side=LEFT)
        self.frameDeDroite = Frame(self.root)
        self.frameMenu = Frame(self.frameDeDroite)
       
        #Menu pour afficher info
        self.frameInfos = Frame(self.frameDeDroite)
        self.label = Label(self.frameMenu, text="Entrer le seed ou loader une configuration.")
        self.label.pack()
        self.seed=Entry(self.frameMenu)
        self.seed.insert(0,random.randint(0, 99999))
        self.seed.pack()
       
        #Label et autre pour affciher info
        self.labelID = Label(self.frameInfos, text="ID: ")
        self.labelID.pack()
       
        self.labelType = Label(self.frameInfos, text="Type: ")
        self.labelType.pack()
       
        self.labelEnergie = Label(self.frameInfos, text="Energie: ")
        self.labelEnergie.pack()
       
        self.labelAge = Label(self.frameInfos, text="Age: ")
        self.labelAge.pack()
       
        self.labelSouffle = Label(self.frameInfos, text="Souffle: ")
        self.labelSouffle.pack()
       
        self.frameInfos.pack(side=BOTTOM)
       
        btnLoad=Button(self.frameMenu,text="Load", command = self.loadConfig)
        btnLoad.pack(side=RIGHT)
       
        testLigne = Label(self.frameMenu, text="                      ")
        testLigne.pack(side=BOTTOM)
       
        # entrys
        self.cadreoutils = Frame(self.frameDeDroite)
        self.nbrloup = Entry(self.cadreoutils)
        self.nbrloup.insert(0, "12")
        self.nbrlapin = Entry(self.cadreoutils)
        self.nbrlapin.insert(0, "15")
        self.nbrlaitue = Entry(self.cadreoutils)
        self.nbrlaitue.insert(0, "30")
        self.nbrraton = Entry(self.cadreoutils)
        self.nbrraton.insert(0, "12")
        self.nbrpandaroux = Entry(self.cadreoutils)
        self.nbrpandaroux.insert(0, "7")
        self.nbrours = Entry(self.cadreoutils)
        self.nbrours.insert(0, "5")
        self.nbrcaribou = Entry(self.cadreoutils)
        self.nbrcaribou.insert(0, "6")
        self.Largeur = Entry(self.cadreoutils)
        self.Largeur.insert(0, "800")
        self.Hauteur = Entry(self.cadreoutils)
        self.Hauteur.insert(0, "600")
 
        # labels
        labelvide = Label(self.cadreoutils, text=" ")
        labelvide2 = Label(self.cadreoutils, text=" ")
        nbrloup = Label(self.cadreoutils, text="Nbr Loup")
        nbrlapin = Label(self.cadreoutils, text="Nbr Lapin")
        nbrlaitue = Label(self.cadreoutils, text="Nbr Laitue")
        nbrraton = Label(self.cadreoutils, text="Nbr Raton")
        nbrpandaroux = Label(self.cadreoutils, text="Nbr Panda roux")
        nbrours = Label(self.cadreoutils, text="Nbr d'ours")
        nbrcaribou = Label(self.cadreoutils, text="Nbr de caribous")
        Largeur = Label(self.cadreoutils, text="Largeur")
        Hauteur = Label(self.cadreoutils, text="Hauteur")
        # l'objet ne sera pas oublié mais on as pas de handle dessus.
        # on ne le controlera pas.(parce que le garbage colelctor attend que rien references

        labelvide2.grid(column=0, row=2)
        nbrloup.grid(column=0, row=3)
        nbrlapin.grid(column=0, row=4)
        nbrlaitue.grid(column=0, row=5)
        nbrraton.grid(column=0, row=6)
        nbrpandaroux.grid(column=0, row=7)
        nbrours.grid(column=0, row=8)
        nbrcaribou.grid(column=0, row=9)
        labelvide.grid(column=0, row=10)

        self.nbrloup.grid(column=1, row=3)
        self.nbrlapin.grid(column=1, row=4)
        self.nbrlaitue.grid(column=1, row=5)
        self.nbrraton.grid(column=1, row=6)
        self.nbrpandaroux.grid(column=1, row=7)
        self.nbrours.grid(column=1, row=8)
        self.nbrcaribou.grid(column=1, row=9)
       
        self.btngo = Button(self.cadreoutils, text="Go", command=self.creerenviro)
        self.btngo.grid(column=0, row=11, columnspan=2)
       
        self.btnSave=Button(self.cadreoutils,text="Save",command=self.saveConfig)
        self.btnSave.grid(column=2,row=11,columnspan=1)
       
        self.btnPause = Button(self.cadreoutils, text="Pause", command=self.arretertimer)
        self.btnPause.grid(column=0, row=13, columnspan=2)
       
        self.btnResume = Button(self.cadreoutils, text="Continuer", command=self.resumetimer)
        self.btnResume.grid(column=0, row=11, columnspan=2)
       
        self.btnAvancer = Button(self.cadreoutils, text="Avancer", command=self.avancertimer)
        self.btnAvancer.grid(column=0, row=12, columnspan=2)
       
        self.cadreoutils.pack(side=BOTTOM)
        self.frameMenu.pack(side=TOP)
        self.frameDeDroite.pack(side=RIGHT)
        
        self.btnResume.grid_forget()
        self.btnAvancer.grid_forget()
       
        self.cadreoutils.pack(side=BOTTOM)
        self.frameMenu.pack(side=TOP)
        self.frameDeDroite.pack(side=RIGHT)
   
        self.clicAvance = Label(self.cadreoutils, text="Nbr de clics")
       
        self.clicAvanceEntre = Entry(self.cadreoutils)
        self.clicAvanceEntre.insert(0, "1")
       
        self.clicAvance.grid(column=0, row=10)
        self.clicAvanceEntre.grid(column=1, row=10)
       
        self.clicAvance.grid_forget()
        self.clicAvanceEntre.grid_forget()
       
    def arretertimer(self):
        self.parent.timeron=0
        
        self.btnPause.grid_forget()
        self.btnResume.grid(column=0, row=11, columnspan=2)
        self.btnAvancer.grid(column=0, row=13, columnspan=2)
       
        self.clicAvance.grid(column=0, row=12)
        self.clicAvanceEntre.grid(column=1, row=12)
           
    def resumetimer(self):
        self.btnResume.grid_forget()
        self.btnAvancer.grid_forget()
        self.btnPause.grid(column=0, row=13, columnspan=2)
        
        self.clicAvance.grid_forget()
        self.clicAvanceEntre.grid_forget()
        
        self.parent.timeron=1
        self.parent.montimer()
       
    def avancertimer(self):
        for i in range(int(self.clicAvanceEntre.get())):
            self.parent.montimer()
             
    def saveConfig(self):
        print("Place")
        with open('saveConfig.dat', 'w') as file_:
            file_.write(str(self.parent.seed))
            file_.write('\n')
            file_.write(self.nbrloup.get())
            file_.write('\n')
            file_.write(self.nbrlapin.get())
            file_.write('\n')
            file_.write(self.nbrlaitue.get())
            file_.write('\n')
            file_.write(self.nbrraton.get())
            file_.write('\n')
            file_.write(self.nbrpandaroux.get())
            file_.write('\n')
            file_.write(self.nbrours.get())
            file_.write('\n')
            file_.write(self.nbrcaribou.get())
       
    def creerenviro(self):
        self.setSeed()
        nloup = self.nbrloup.get()
        nlapin = self.nbrlapin.get()
        nlaitue = self.nbrlaitue.get()
        nraton = self.nbrraton.get()
        npandaroux = self.nbrpandaroux.get()
        nours=self.nbrours.get()
        ncaribou=self.nbrcaribou.get()
        self.parent.creerenviro(nloup, nlapin, nlaitue, nraton, npandaroux, nours, ncaribou, self.parent.Modele.largeur, self.parent.Modele.hauteur)
       
    def loadimage(self, enviro): 
        self.loupmaledevant = PhotoImage(file = 'images/Loupmalefront.png')
        self.loupmalederriere = PhotoImage(file = 'images/Loupmaleback.png')
        self.loupmaledroit = PhotoImage(file = 'images/Loupmaledroite.png')
        self.loupmalegauche = PhotoImage(file = 'images/Loupmalegauche.png')
        
        self.loupfemelledevant = PhotoImage(file = 'images/Loupfemellefront.png')
        self.loupfemellederriere = PhotoImage(file = 'images/Loupfemelleback.png')
        self.loupfemelledroit = PhotoImage(file = 'images/Loupfemelledroite.png')
        self.loupfemellegauche = PhotoImage(file = 'images/Loupfemellegauche.png')
        
        self.lapindevant = PhotoImage(file = 'images/Lapinfront.png')
        self.lapinderriere = PhotoImage(file = 'images/Lapinback.png')
        self.lapindroit = PhotoImage(file = 'images/Lapindroite.png')
        self.lapingauche = PhotoImage(file = 'images/Lapingauche.png')
        
        self.lapinedevant = PhotoImage(file = 'images/Lapinefront.png')
        self.lapinederriere = PhotoImage(file = 'images/Lapineback.png')
        self.lapinedroit = PhotoImage(file = 'images/Lapinedroite.png')
        self.lapinegauche = PhotoImage(file = 'images/Lapinegauche.png')
        
        self.pandadevant = PhotoImage(file = 'images/pandafront.png')
        self.pandaderriere = PhotoImage(file = 'images/pandaback.png')
        self.pandadroit = PhotoImage(file = 'images/pandadroite.png')
        self.pandagauche = PhotoImage(file = 'images/pandagauche.png')
        
        self.ratondevant = PhotoImage(file = 'images/raccoonfront.png')
        self.ratonderriere = PhotoImage(file = 'images/raccoonback.png')
        self.ratondroit = PhotoImage(file = 'images/raccoondroite.png')
        self.ratongauche = PhotoImage(file = 'images/raccoongauche.png')
        
        self.oursdevant = PhotoImage(file = 'images/oursdevant.png')
        self.oursderriere = PhotoImage(file = 'images/oursderriere.png')
        self.oursdroit = PhotoImage(file = 'images/oursdroite.png')
        self.oursgauche = PhotoImage(file = 'images/oursgauche.png')
       
        self.cariboudevant = PhotoImage(file = 'images/orignaldevant.png')
        self.caribouderriere = PhotoImage(file = 'images/orignalderriere.png')
        self.cariboudroit = PhotoImage(file = 'images/orignaldroite.png')
        self.caribougauche = PhotoImage(file = 'images/orignalgauche.png')
        
        self.bebelaitue = PhotoImage(file = 'images/bebeletuce.png')
        self.laitue = PhotoImage(file = 'images/letuce.png')
        self.laituemorte = PhotoImage(file = 'images/dyingletuce.png')
        
        self.bebeBaies = PhotoImage(file = 'images/bebeletuce.png')
        self.baies = PhotoImage(file = 'images/baie1.png')
        self.baiesMortes = PhotoImage(file = 'images/baie2.png')
        
        self.nuit = PhotoImage(file='images/filtrenuit.png')
        
        self.eau1=PhotoImage(file='images/eau1.png')
        self.eau2=PhotoImage(file='images/eau2.png')
        self.eau3=PhotoImage(file='images/eau3.png')
        self.eaugelee = PhotoImage(file='images/eaugelee.png')
      
    def afficheenviroInstantanne(self, enviro):
        self.canevas.delete("chose", "eau")
       
        for i in enviro.loups:
            self.canevas.create_image(i.x,i.y, image = i.imageActuelle,tags=("chose", "Loup",))
           
        for i in enviro.lapins:
            self.canevas.create_image(i.x,i.y, image = i.imageActuelle, tags=("chose", "Lapin",))
       
        for i in enviro.laitues:
            if i.stade == 0:
                self.canevas.create_image(i.x,i.y, image = self.bebelaitue,tags=("chose", "Laitue",))
            elif i.stade == 1:
                self.canevas.create_image(i.x,i.y, image = self.laitue,tags=("chose", "Laitue",))
            else:
                self.canevas.create_image(i.x,i.y, image = self.laituemorte,tags=("chose", "Laitue",))
                
        for i in enviro.baies:
            if i.stade == 0:
                self.canevas.create_image(i.x,i.y, image = self.bebeBaies,tags=("chose", "Baies d'hiver",))
            elif i.stade == 1:
                self.canevas.create_image(i.x,i.y, image = self.baies,tags=("chose", "Baies d'hiver",))
            else:
                self.canevas.create_image(i.x,i.y, image = self.baiesMortes,tags=("chose", "Baies d'hiver",))
       
        for i in enviro.ratons:
            self.canevas.create_image(i.x,i.y, image = i.imageActuelle,tags=("chose", "Raton",))
       
        for i in enviro.pandarouxs:
            self.canevas.create_image(i.x,i.y, image = i.imageActuelle,tags=("chose", "Panda",))
        
        for i in enviro.lesOurs:
            self.canevas.create_image(i.x,i.y, image = i.imageActuelle,tags=("chose", "Ours",))
       
        for i in enviro.caribous:
            self.canevas.create_image(i.x,i.y, image = i.imageActuelle,tags=("chose", "Caribou",))
            
        for i in enviro.coursDeau:
            self.canevas.create_image(i.centreX,i.centreY, image = i.imageActuelle,tags=("chose", "eau", "eau"))   
           
    def afficheenviroJournalie(self, enviro, jour):
        if jour==False:
            self.canevas.create_image(self.parent.Modele.largeur/2,self.parent.Modele.hauteur/2, image = self.nuit, tags = "filtre")
        else:
            self.canevas.delete("filtre")
            
    def afficheenviroSaisonnie(self, enviro, laSaison, largeur, hauteur):
        self.canevas.delete("relief")
        self.canevas.delete("eau")

        if laSaison == "ete":
            #self.canevas.create_image(self.largeur/2,self.hauteur/2, image = self.etejour)
            self.canevas.configure(bg=enviro.couleur)
            self.canevas["bg"] = enviro.couleur
            for i in enviro.Ravins:
                self.canevas.create_oval(i.x, i.y, i.x + i.largeur, i.y + i.hauteur, fill="dark olive green",
                                         tags=("relief", "Ravin",))
            for i in enviro.Montagnes:
                self.canevas.create_oval(i.x-i.rayon, i.y+i.rayon, i.x + i.rayon, i.y - i.rayon, fill="forest green",
                                         tags=("relief", "Montagne",))

        elif laSaison == "automne":
            #self.canevas.create_image(self.largeur/2,self.hauteur/2, image = self.etejour)
            self.canevas.configure(bg=enviro.couleur)
            self.canevas["bg"] = enviro.couleur
            for i in enviro.Ravins:
                self.canevas.create_oval(i.x, i.y, i.x + i.largeur, i.y + i.hauteur, fill="gray34",
                                         tags=("relief", "Ravin",))
            for i in enviro.Montagnes:
                self.canevas.create_oval(i.x-i.rayon, i.y+i.rayon, i.x + i.rayon, i.y - i.rayon, fill="grey45",
                                         tags=("relief", "Montagne",))
            
        elif laSaison == "hiver":
            #self.canevas.create_image(self.largeur/2,self.hauteur/2, image = self.etejour)
            self.canevas.configure(bg=enviro.couleur)
            self.canevas["bg"] = enviro.couleur
            for i in enviro.Ravins:
                self.canevas.create_oval(i.x, i.y, i.x + i.largeur, i.y + i.hauteur, fill="grey45",
                                         tags=("relief", "Ravin",))
            for i in enviro.Montagnes:
                self.canevas.create_oval(i.x-i.rayon, i.y+i.rayon, i.x + i.rayon, i.y - i.rayon, fill="grey60",
                                         tags=("relief", "Montagne",))
            
        elif laSaison == "printemps":
            #self.canevas.create_image(self.largeur/2,self.hauteur/2, image = self.etejour)
            self.canevas.configure(bg=enviro.couleur)
            self.canevas["bg"] = enviro.couleur
            for i in enviro.Ravins:
                self.canevas.create_oval(i.x, i.y, i.x + i.largeur, i.y + i.hauteur, fill="grey34",
                                         tags=("relief", "Ravin",))
            for i in enviro.Montagnes:
                self.canevas.create_oval(i.x-i.rayon, i.y+i.rayon, i.x + i.rayon, i.y - i.rayon, fill="olive drab",
                                         tags=("relief", "Montagne",))
                
    def afficheenviroPermanent(self, enviro):
        pass
    
    def setSeed(self):
        self.parent.seed = self.seed.get()
        random.seed(self.seed.get())
   
    def loadConfig(self):
        print("Placeholder")
        array = []
        if os.path.isfile("saveConfig.dat"):
            print("Is a file")
            with open("saveConfig.dat") as f:
                for line in f:
                    array.append(line.rstrip())
                random.seed(array[0])
                self.seed.delete(0, END)
                self.nbrloup.delete(0,END)
                self.nbrlapin.delete(0,END)
                self.nbrraton.delete(0,END)
                self.nbrpandaroux.delete(0,END)
                self.nbrours.delete(0,END)
                self.nbrcaribou.delete(0,END)
                self.nbrlaitue.delete(0,END)
                self.nbrbaies.delete(0,END) 
                          
                self.seed.insert(0, array[0])
                self.nbrloup.insert(0,array[1])
                self.nbrlapin.insert(0,array[2])
                self.nbrlaitue.insert(0,array[3])
                self.nbrraton.insert(0,array[4])
                self.nbrpandaroux.insert(0,array[5])
                self.nbrours.insert(0,array[6])
                self.nbrcaribou.insert(0,array[7])
                self.nbrbaies.insert(0,array[8])
        else:
            print("File not found.")
            
    def printcoords(self, event):
        #outputting x and y coords to console
#        print (event.x, event.y)
 
        if (self.parent.Modele.environnement):
            #Il me faut les animaux
            loups = self.parent.Modele.environnement.loups
            lapins = self.parent.Modele.environnement.lapins
            laitues = self.parent.Modele.environnement.laitues
            ratons = self.parent.Modele.environnement.ratons
            pandas = self.parent.Modele.environnement.pandarouxs
            lesOurs = self.parent.Modele.environnement.lesOurs
            caribous = self.parent.Modele.environnement.caribous
            baies = self.parent.Modele.environnement.baies
           
            self.ObjTag = None
            tagsOfObject = None
           
            obj = event.widget.find_overlapping(event.x, event.y, event.x, event.y)
           
            for id in event.widget.find_overlapping(event.x, event.y, event.x, event.y):
                tagsOfObject = self.canevas.itemcget(id, "tags")
           
            if tagsOfObject:
                self.tagsOfObject = tagsOfObject.split()
           
                print(self.tagsOfObject[1])
           
                if self.tagsOfObject[1] == "Loup":
                    for i in loups:
                        if event.x - 7 <= i.x <= event.x+7 and event.y - 7 <= i.y <= event.y+7:
                            self.ObjTag = i
                           
                elif self.tagsOfObject[1] == "Lapin":
                    for i in lapins:
                        if event.x - 7 <= i.x <= event.x+7 and event.y - 7 <= i.y <= event.y+7:
                            self.ObjTag = i
                           
                elif self.tagsOfObject[1] == "Laitue":
                    for i in laitues:
                        if event.x - 7 <= i.x <= event.x+7 and event.y - 7 <= i.y <= event.y+7:
                            self.ObjTag = i
                
                elif self.tagsOfObject[1] == "Baies d'hiver":
                    for i in baies:
                        if event.x - 7 <= i.x <= event.x+7 and event.y - 7 <= i.y <= event.y+7:
                            self.ObjTag = i
                           
                elif self.tagsOfObject[1] == "Raton":
                    for i in ratons:
                        if event.x - 7 <= i.x <= event.x+7 and event.y - 7 <= i.y <= event.y+7:
                            self.ObjTag = i
                                           
                elif self.tagsOfObject[1] == "Panda":
                    for i in pandas:
                        if event.x - 7 <= i.x <= event.x+7 and event.y - 7 <= i.y <= event.y+7:
                            self.ObjTag = i
                
                elif self.tagsOfObject[1] == "Ours":
                    for i in lesOurs:
                        if event.x - 7 <= i.x <= event.x+7 and event.y - 7 <= i.y <= event.y+7:
                            self.ObjTag = i
               
                elif self.tagsOfObject[1] == "Caribou":
                    for i in caribous:
                        if event.x - 7 <= i.x <= event.x+7 and event.y - 7 <= i.y <= event.y+7:
                            self.ObjTag = i
                       
            if (self.ObjTag):
                print("ID: " + str(self.ObjTag.id))
                print("Type: " +str(self.ObjTag.typeAnimal))
                print("Energie: " + str(self.ObjTag.energie))
                print("Age: " + str(self.ObjTag.age))
                print("Souffle: " + str(self.ObjTag.souffle))
           
                self.labelID.config(text = ("ID: " + str(self.ObjTag.id)))
                self.labelType.config(text = ("Type: " + str(self.ObjTag.typeAnimal)))
                self.labelEnergie.config(text = ("Energie: " + str(self.ObjTag.energie)))
                self.labelAge.config(text = ("Age: " + str(self.ObjTag.age)))
                self.labelSouffle.config(text = ("Souffle: " + str(self.ObjTag.souffle)))
 
class Controleur():                            
    def __init__(self):
        self.seed = 10                          #Truc de seed
        self.useSaved = False
        self.retour = []
        self.currentTick = 0
       
       
        random.seed(self.seed)
        self.Modele=Monde(self, 800,600)
        self.vue=Vue(self,self.Modele.largeur,self.Modele.hauteur)                  #ON SE DONNE A LA VUE POUR DONNER PARENT
        largeur = self.vue.largeur
        hauteur= self.vue.hauteur
        
        self.timercourant=None
        self.vue.root.mainloop()
       
    def creerenviro(self,loup,lapin,laitue,raton,pandaroux,ours, caribou, largeur, hauteur):
        #quand on créé l'environ, si on timer existe, on le cancel d'abord
        self.Modele.changesize(largeur, hauteur)
        if self.timercourant:
            self.vue.root.after_cancel(self.timercourant)
        self.Modele.creerenviro(loup,lapin,laitue,raton,pandaroux, ours, caribou)
        self.vue.afficheenviroPermanent(self.Modele.environnement)
        self.vue.afficheenviroSaisonnie(self.Modele.environnement, "ete", self.Modele.largeur, self.Modele.hauteur)
        self.vue.afficheenviroJournalie(self.Modele.environnement, True)
        self.vue.afficheenviroInstantanne(self.Modele.environnement)
        self.timeron=1
        #demarer le timer
        self.montimer()
       
    def montimer(self):
        self.retour = self.Modele.evolue()
       
        if self.retour[0]==True:
            self.vue.afficheenviroInstantanne(self.Modele.environnement)
        if self.retour[1]==True:
            self.vue.afficheenviroJournalie(self.Modele.environnement, self.Modele.environnement.Jour)
        if self.retour[2]==True:
            self.vue.afficheenviroSaisonnie(self.Modele.environnement, self.Modele.environnement.saison, self.Modele.largeur, self.Modele.hauteur)
        if self.retour[3]==True:
            self.vue.afficheenviroPermanent(self.Modele.environnement)
           
        self.createLog()  
       
       
        if self.timeron:
            self.timercourant = self.vue.root.after(16, self.montimer)
            self.currentTick+=1
           
           
    def createLog(self):
        #je get les listes pour les utiliser pour mon log
        self.loups = self.Modele.environnement.loups
        self.lapins = self.Modele.environnement.lapins
        self.laitues = self.Modele.environnement.laitues
        self.ratons = self.Modele.environnement.ratons
        self.pandas = self.Modele.environnement.pandarouxs
        self.lesOurs = self.Modele.environnement.lesOurs
        self.caribous = self.Modele.environnement.caribous

       
       
       
        if self.currentTick% (10*60) == 10:
            self.log = "Current Timer Tick: " + str(self.currentTick)
            self.log += "\nNombre de loups: " + str(len(self.loups))
            self.log += "\nNombre de lapins: " + str(len(self.lapins))
            self.log += "\nNombre de laitues: " + str(len(self.laitues))
            self.log += "\nNombre de ratons: " + str(len(self.ratons))
            self.log += "\nNombre de pandas: " + str(len(self.pandas))
            self.log += "\nNombre d'ours: " + str(len(self.lesOurs))
            self.log += "\nNombre de caribous: " + str(len(self.caribous))

            self.log += "\n\n"
            print(self.log)
            with open('log.txt', 'a') as file_:
                file_.write(self.log)
 
 
#on fait le main qui launch le controleur        
if __name__ == '__main__':
    c=Controleur()