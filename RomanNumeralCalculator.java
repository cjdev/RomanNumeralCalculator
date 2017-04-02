package com.company;

public class RomanNumeralCalculator {
    
        public static String repo[] ={"I","V","X","L","C","D","M","i","v","x","l","c","d","m"};
        
        public static void main(String[] args) {
            System.out.println("hello world");
        }
        
        public static String addRomanNumerals(String... args){
            double sum=0;
            for(int i=0;i<args.length;i++)
            {
                sum=sum+convert(args[i]);
            }
            return convertback(sum);
        }
        
        public static String subtractRomanNumerals(String initial, String... rest){
            double sum=convert(initial);
            for(int i=0;i<rest.length;i++)
            {
                sum=sum-convert(rest[i]);
            }
            return convertback(sum);
        }
        
        public static String multiplyRomanNumerals(String... args){
            double product=1;
            for(int i=0;i<args.length;i++)
            {
                product=product*convert(args[i]);
            }
            return convertback(product);
        }
        
        public static String divideRomanNumerals(String numerator , String... denominators){
            double num=convert(numerator);
            for(int i=0;i<denominators.length;i++)
            {
                if(denominators[i].equals("nulla"))
                    return "nan";
                num=num/convert(denominators[i]);
            }
            return convertback(num);
        }
        
        public static double convert(String s)
        {
            double sum=0;
            int p1=0,p2,f=0;
            if(s.charAt(0)=='-')
            {
                p1=1;
                f=1;
            }
            String split[]=s.split(" ");
            if(s.equals("nulla"))
                return 0;
            int f1=0;
            for(int i=0;i<split[0].length();i++)
            {
                if(split[0].charAt(i)=='/')
                {
                    f1=1;
                }
            }
            if (f1!=1)
            {
                for(int j=12;j>=0;j--)
                {
                    for(int i=p1;i<split[0].length();i++)
                    {
                        if(split[0].charAt(i)==repo[j].charAt(0))
                        {
                            p2=i;
                            if(p2!=p1)
                            {
                                if(repo[j].charAt(0)=='M'||repo[j].charAt(0)=='m')
                                {
                                    sum+=900;
                                }
                                if(repo[j].charAt(0)=='D'||repo[j].charAt(0)=='d')
                                {
                                    sum+=400;
                                }
                                if(repo[j].charAt(0)=='C'||repo[j].charAt(0)=='c')
                                {
                                    sum+=90;
                                }
                                if(repo[j].charAt(0)=='L'||repo[j].charAt(0)=='l')
                                {
                                    sum+=40;
                                }
                                if(repo[j].charAt(0)=='X'||repo[j].charAt(0)=='x')
                                {
                                    sum+=9;
                                }
                                if(repo[j].charAt(0)=='V'||repo[j].charAt(0)=='v')
                                {
                                    sum+=4;
                                }
                                if(repo[j].charAt(0)=='I'||repo[j].charAt(0)=='i')
                                {
                                    sum+=1;
                                }
                            }
                            else
                            {
                                if(repo[j].charAt(0)=='M'||repo[j].charAt(0)=='m')
                                {
                                    sum+=1000;
                                }
                                if(repo[j].charAt(0)=='D'||repo[j].charAt(0)=='d')
                                {
                                    sum+=500;
                                }
                                if(repo[j].charAt(0)=='C'||repo[j].charAt(0)=='c')
                                {
                                    sum+=100;
                                }
                                if(repo[j].charAt(0)=='L'||repo[j].charAt(0)=='l')
                                {
                                    sum+=50;
                                }
                                if(repo[j].charAt(0)=='X'||repo[j].charAt(0)=='x')
                                {
                                    sum+=10;
                                }
                                if(repo[j].charAt(0)=='V'||repo[j].charAt(0)=='v')
                                {
                                    sum+=5;
                                }
                                if(repo[j].charAt(0)=='I'||repo[j].charAt(0)=='i')
                                {
                                    sum+=1;
                                }
                            }
                            p1=p2+1;
                            j=12;
                        }
                    }
                }
                int num=1,den=1;
                double div=0;
                if(split.length!=1)
                {
                    for(int i=0;i<split[1].length();i++)
                    {
                        if(split[1].charAt(i)=='/')
                        {
                            num = (int)convert(split[1].substring(0,i));
                            den = (int) convert(split[1].substring(i+1));
                        }
                    }
                    div=(num*1.0)/den;
                }
                if(f==1)
                    return (sum+div)*(-1);
                else
                    return sum+div;
            }
            else
            {
                int num=1,den=1;
                double div=0;
                for(int i=0;i<split[0].length();i++)
                {
                    if(split[0].charAt(i)=='/')
                    {
                        num = (int)convert(split[0].substring(0,i));
                        den = (int) convert(split[0].substring(i+1));
                    }
                }
                div=(num*1.0)/den;
                if(f==1)
                    return (sum+div)*(-1);
                else
                    return sum+div;
            }
        }
        
        public static String convertback(double n)
        {
            int no=0;
            int f=0;
            String s="";
            int num;
            if(n<0)
            {
                f=1;
                n*=(-1);
            }
            if(n==0)
                return "nulla";
            int first=(int)n;
            int last=(int)(n*1000)%1000;
            last+=5;
            if(last%10<5)
                last/=10;
            else
                last=(last-5)/10;
            int copy=first;
            while(copy!=0)
            {
                no++;
                copy/=10;
            }
            if(no==4)
            {
                num=first/(int)Math.pow(10,3);
                for(int j=0;j<num;j++)
                {
                    s=s+"M";
                }
                first-=(num*1000);
                no--;
            }
            if(no==3)
            {
                num=first/(int)Math.pow(10,2);
                if(num==9)
                {
                    s=s+"CM";
                }
                if(num>=5&&num<9)
                {
                    s=s+"D";
                    for(int j=0;j<num-5;j++)
                    {
                        s=s+"C";
                    }
                }
                if(num==4)
                {
                    s=s+"CD";
                }
                if(num<4)
                {
                    for(int j=0;j<num;j++)
                    {
                        s=s+"C";
                    }
                }
                first-=(num*100);
                no--;
            }
            if(no==2)
            {
                num=first/(int)Math.pow(10,1);
                if(num==9)
                {
                    s=s+"XC";
                }
                if(num>=5&&num<9)
                {
                    s=s+"L";
                    for(int j=0;j<num-5;j++)
                    {
                        s=s+"X";
                    }
                }
                if(num==4)
                {
                    s=s+"XL";
                }
                if(num<4)
                {
                    for(int j=0;j<num;j++)
                    {
                        s=s+"X";
                    }
                }
                first-=(num*10);
                no--;
            }
            if(no==1)
            {
                num=first;
                if(num==9)
                {
                    s=s+"IX";
                }
                if(num>=5&&num<9)
                {
                    s=s+"V";
                    for(int j=0;j<num-5;j++)
                    {
                        s=s+"I";
                    }
                }
                if(num==4)
                {
                    s=s+"IV";
                }
                if(num<4)
                {
                    for(int j=0;j<num;j++)
                    {
                        s=s+"I";
                    }
                }
                first-=(num);
            }
            if(last!=0)
            {
                s=s+simplify(last);
            }
            if(f==1)
            {
                return "-"+s;
            }
            else
            {
                return s;
            }
        }
        
        public static String simplify(int n)
        {
            int d=100;
            int copy=n;
            for(int i=2;i<=copy/2;i++)
            {
                if(n%i==0 && d%i==0)
                {
                    n/=i;
                    d/=i;
                    i--;
                }
            }
            String s1=convertback(n),s2=convertback(d);
            return (" "+s1.toLowerCase()+"/"+s2.toLowerCase());
        }
    }
    
