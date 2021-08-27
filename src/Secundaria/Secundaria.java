package Secundaria;

public class Secundaria {
	
	int primo;
	int descomponer;
	int valor;
	boolean neg=false;
	
	public void ingresar(int a)
	{
		// crear numero positivo
		primo = 2;
		if(a<=-1) {
			a*=-1;
			neg=true;
		}
		this.descomponer=a;
	}
		
	public int calcularPrimo()
	{
		primo++;
		if(primo!=valor) 
		{
			for (int i = 2; i<primo; i++)
			{
				if(primo%i==0)
				{
					primo=calcularPrimo();
				}
			}
		}	
		return primo;
	}
	
	public String descomponer()
	{
		String resultado="\nLa descomposicion de "+descomponer+",\nes igual a:\n";
		String resultado1="";
		if(neg)
		{
			resultado+="Factor de negatividad: -1\n";
			resultado1+="Operacion:"+(-descomponer)+"*-1 = "+descomponer+"\n";
			//imprimir("Operacion: -1*"+(-descomponer)+"= "+descomponer);
		}
		valor = descomponer;
		
		while(true)
		{
			if(valor%primo==0)
			{
				resultado+= "Factor: "+primo+"\n";
				int r =valor;
				valor/=primo;
				
				resultado1+="Operacion: "+r+"/"+primo+"= "+valor+"\n";
				//imprimir("Operacion: "+r+"/"+primo+"= "+valor);
			}
			else
			{
				calcularPrimo();
			}
			if(primo==valor)
			{
				resultado += "Factor: "+valor+"\n";
				int r =valor;
				valor/=primo;
				
				resultado1+="Operacion: "+r+"/"+primo+"= "+valor+"\n";
				//imprimir("Operacion: "+r+"/"+primo+"= "+valor);			
				break;
			}
			else if(valor==1)
			{
				break;
			}
		}
		//imprimir(resultado);
		
		return resultado1+resultado;
	}
	
	/* public void imprimir(String s)
	{
		System.out.println(s);
	} */
	
}
