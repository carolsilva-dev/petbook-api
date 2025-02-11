CREATE TABLE public.pet (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INTEGER NOT NULL,
    apelido VARCHAR (50),
    raca VARCHAR (50),
    genero VARCHAR(50) NOT NULL,
    dono_id UUID NOT NULL,
    CONSTRAINT fk_pet_usuario FOREIGN KEY (dono_id) REFERENCES public.usuario(id) ON DELETE CASCADE
);
