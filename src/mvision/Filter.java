package mvision;

import java.io.File;
import java.io.FileFilter;


class Filter implements FileFilter
{
		String[] ext;
		Filter(String[] ext)
		{
			this.ext=ext;
		}
		public boolean accept(File name)
		{
			boolean flag=false;
			for(int i=0;i<ext.length;i++)
			{
				if(name.getName().endsWith(ext[i]))
				{
					flag=true;
					break;
				}
			}
			return flag;
		}
		 public String getDescription()
		 {
			 return "image File";
		 }
	}