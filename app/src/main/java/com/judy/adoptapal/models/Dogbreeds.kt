package com.judy.adoptapal.models

data class DogBreed(val name: String, val imageUrl: String)

val dogBreedsInKenya = listOf(
    DogBreed("German Shepherd", "https://images.unsplash.com/photo-1649923625793-af1cbd33c8bd?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Rottweiler", "https://images.unsplash.com/photo-1655064320103-114b997a87b3?q=80&w=1970&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Boerboel", "https://images.unsplash.com/photo-1594738985551-b2e28db213a0?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Golden Retriever", "https://images.unsplash.com/photo-1730019816520-5686aa34cf75?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Labrador Retriever", "https://images.unsplash.com/photo-1672838564959-2acf52d97197?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Japanese Spitz", "https://plus.unsplash.com/premium_photo-1718652942341-3cbe0512171e?q=80&w=2072&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Chihuahua", "https://plus.unsplash.com/premium_photo-1677181729027-a7873c1f6cb5?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Samoyed", "https://images.unsplash.com/photo-1697125455111-a0a23fdb8df8?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Doberman Pinscher", "https://media.istockphoto.com/id/462496783/photo/doberman-pinscher.jpg?s=1024x1024&w=is&k=20&c=2Keyd8TIhlOChSUyNLE4NPBBExXwSU3amfxNwtFqs-M="),
    DogBreed("Belgian Malinois", "https://images.unsplash.com/photo-1655474982427-42b70faff1fc?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Great Dane", "https://images.unsplash.com/photo-1676290724566-a0ee7b4ee0a9?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Cocker Spaniel", "https://plus.unsplash.com/premium_photo-1661962970543-1ee557960078?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Pug", "https://images.unsplash.com/photo-1568807486303-a8f9c2ce6221?q=80&w=1925&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Maltese", "https://images.unsplash.com/photo-1718101688401-c6bc867105b3?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Jack Russell Terrier", "https://images.unsplash.com/photo-1677243098071-d3915eb266e0?q=80&w=2112&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Dachshund", "https://images.unsplash.com/photo-1641256979532-4579084556c4?q=80&w=1965&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Bichon Frise", "https://images.unsplash.com/photo-1716158690308-25a97fc47261?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("American Pit Bull Terrier", "https://images.unsplash.com/photo-1635076025932-f336401c4227?q=80&w=2000&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    DogBreed("Irish Setter", "https://images.unsplash.com/photo-1693674648122-7f6bd013dad8?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
)
