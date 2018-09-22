# aws-sudo-intellij-plugin
Intellij plugin with run configuration for aws-sudo command

So this is a quick copy/paste/hack from a non-java guy.  I work with AWS, Terraform, and Intellij quite a lot.  The issue for me is, i work with many different clients.  To handle switching AWS context i utilize a tool called `aws-sudo`.  There are many variation of this tool, I personally like this one:

https://github.com/voytek-solutions/aws-sudo

It can be installed using `pip install aws-sudo`

With mfa and multiple profiles, correct setup of the AWS credentials file is key, but then switching context using aws-sudo is straight froward.  Just pass aws-sudo the profile name from aws credentials file and then the command you want to run.

Examples
- aws-sudo client aws s3 ls
- aws-sudo client terraform plan -out=plan.out
- aws-sudo client ansible-playbook

So now onto Intellij...  Terraform for example has a plugin, which works great for me when it comes to intellisense, validation, etc.  But not so much on the run/debug configurations.  As these would lack the context i mentioned previously.  So, if i was working on some terraform for a client, to run a plan...  I would, have to click down on the terminal tab or have a separate window open and just type out the command.

To save some clicking/typing, it would be nice to have it built in as a run/debug configuration.

## run/debug configuration drop-down
![](/docs/config-menu.png)

## edit run/debug configuration
![](/docs/edit-config.png)

