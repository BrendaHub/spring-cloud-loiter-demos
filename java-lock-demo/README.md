乐观锁和悲观锁是两种思想， 用于解决并发场景下的数据竞争问题。 

- 乐观锁： 乐观锁在操作数据时非常乐观， 认为别人不会同时修改数据。 因些乐观锁不会上锁， 只是在执行更新的时候判断一下在些期间别人是否修改了数据， 如果别人修改了数据则放弃操作， 否则执行操作； 

- 悲观锁： 悲观锁在操作数据时比较悲观， 认为别人会同时修改数据。 因此操作数据时直接把数据锁住， 直到操作完成后才会释放锁； 上锁期间其他们不会修改数据。 

## 实现方式

在说明实现方式之前， 需要明确： 乐观锁和悲观锁是两种思想， 它们的使用是非常广泛的不司限于某种编程语言或数据库。 

- 悲观锁的实现方式是加锁， 加锁即可以对代码块加锁，（如java的Synchronized关键字）， 也可以是对数据加锁（如MySQL中的排它锁）。 

- 乐观锁的实现方式主要有两种： CAS机制和版本号机制。 

## CAS（Compare And Swap)

- 需要读写的内存位置（V）

- 进行比较的预期值（A）

- 拟写入的新值（B）

CAS 操作逻辑如下： 

如果内存位置V的值 于预期的A值， 则将更新为新值B。否则不进行任何操作。 许多CAS的操作是自旋的。 如果操作不成功， 会一直重试， 直到操作成功为止。 

这就引出了一个新的问题，即然CAS包含了Compare和Swap两个操作， 
它又如何保证原子性呢？ 
答案是： CAS是由CPU支持的原子操作， 其原子性是在硬件层面进行保证的。 

与悲观锁相比， 乐观锁适用的场景受到了更多的限制， 无论是CAS还是版本号机制。 

如果悲观锁和乐观锁都可以使用， 那么选择就要考虑竞争的激烈程序：  

- 当竞争不激烈（出现并发冲突的概率小）时， 乐观锁更有优势， 因为悲观锁会锁住代码块或数据， 
其他线程无法同时访问， 影响并发， 而且加锁和释放锁都需要消耗额外的资源。 

- 当竞争激烈（出现并发冲突的概率大）时， 悲观锁更有优势， 因为乐观锁在执行更新时频繁失败，需要不断重试， 浪费CPU资源。

### 乐观锁加锁吗？

- 乐观锁本身是不加锁的， 只是在更新时判断一下数据是否被其他线程更新了； AtomicInteger便是一个例子。 

- 有时乐观锁可能与加锁操作合作， 例如， 在前述updateCoins()的例子中， MySQL在执行update时会加排它锁。 但这只是乐观锁与加锁操作合作的例子， 不能改变"乐观锁本身不加锁"的这一事实。 

### CAS一些不那委完美的地方：

- ABA问题

- 高竞争下的开销问题， 引入退出机制， 更重要的是避免在高竞争环境下使用乐观锁

- 功能限制  



